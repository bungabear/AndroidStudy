package com.bungabear.androidstudy;

import org.junit.Test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class gRPCClient {

    @Test
    public void main(){
        // 서버 연결 설정
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 80)
                .usePlaintext()
                .build();


        // simple req/res
        // proto 객체는 builder 패턴으로 작성
        gRPCSample.Request simpleRequest = gRPCSample
                .Request
                .newBuilder()
                .setCmd("simple request")
                .build();

        // blockingStub을 통해 함수처럼 호출
        gRPCSample.Response simpleRes = GRPCSampleGrpc
                .newBlockingStub(channel)
                .simple(simpleRequest);


        // toStream
        // 서버로 보내는 스트림
        StreamObserver<gRPCSample.Request> toStream = GRPCSampleGrpc
                .newStub(channel)
                .toStream(
                        new StreamObserver<gRPCSample.Response>() {
                            @Override
                            public void onNext(gRPCSample.Response value) {
                                // 서버에서 stream 수신
                                System.out.println("Client got toStream response " + value.getCmd());
                            }

                            @Override
                            public void onError(Throwable t) {
                                System.out.println("toStream Client 연결 에러 " + t);
                            }

                            @Override
                            public void onCompleted() {
                                System.out.println("toStream Client 연결 종료");
                            }
                        }
                );

        // 서버로 스트림 전송
        toStream.onNext(gRPCSample.Request.newBuilder().setCmd("toStream 1st request").build());
        toStream.onNext(gRPCSample.Request.newBuilder().setCmd("toStream 2nd request").build());
        toStream.onNext(gRPCSample.Request.newBuilder().setCmd("toStream 3rd request").build());
        // 직접 연결 종료
        toStream.onCompleted();

        // fromStream

        // 서버에서 받는 스트림
        GRPCSampleGrpc
                .newStub(channel)
                .fromStream(gRPCSample.Request.newBuilder().setCmd("fromStream request").build(),
                        new StreamObserver<gRPCSample.Response>() {
                            @Override
                            public void onNext(gRPCSample.Response value) {
                                // 서버에서 stream 수신
                                System.out.println("Client got toStream response " + value.getCmd());
                            }

                            @Override
                            public void onError(Throwable t) {
                                System.out.println("fromStream Client 연결 에러 " + t);
                            }

                            @Override
                            public void onCompleted() {
                                System.out.println("fromStream Client 연결 종료");
                            }
                        }
                );



        // biStream
        StreamObserver<gRPCSample.Response> responseStreamObserver = new StreamObserver<gRPCSample.Response>() {
            @Override
            public void onNext(gRPCSample.Response value) {
                // 서버에서 stream 수신
                System.out.println("Client got toStream response " + value.getCmd());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("biStream Client 연결 에러 " + t);
            }

            @Override
            public void onCompleted() {
                System.out.println("biStream Client 연결 종료");
            }
        };

        // 서버와 주고받는 스트림
        StreamObserver<gRPCSample.Request> biStream = GRPCSampleGrpc
                .newStub(channel)
                .biStream(responseStreamObserver);

        // 서버에 stream 전송
        biStream.onNext(gRPCSample.Request.newBuilder().setCmd("biStream 1st request").build());
        biStream.onNext(gRPCSample.Request.newBuilder().setCmd("biStream 2nd request").build());
        biStream.onNext(gRPCSample.Request.newBuilder().setCmd("biStream 3rd request").build());
    }
}
