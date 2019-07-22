package com.bungabear.androidstudy;

import org.junit.Test;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class gRPCServer {

    private Server server;
    @Test
    public void main(){
        try {
            server = ServerBuilder
                    .forPort(80)
                    .addService(new GRPCSampleImpl())
                    .build()
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class GRPCSampleImpl extends GRPCSampleGrpc.GRPCSampleImplBase{

        @Override
        public StreamObserver<gRPCSample.Request> toStream(StreamObserver<gRPCSample.Response> responseObserver) {
            return new StreamObserver<gRPCSample.Request>(){
                @Override
                public void onNext(gRPCSample.Request value) {
                    System.out.println("Server got toStream request " + value.getCmd());
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("toStream Server 연결 에러 " + t);
                }

                @Override
                public void onCompleted() {
                    System.out.println("toStream Server 연결 종료");
                }
            };
        }

        @Override
        public void fromStream(gRPCSample.Request request, StreamObserver<gRPCSample.Response> responseObserver) {
            // 클라이언트 요청 수신
            System.out.println("fromStream request : " + request.getCmd());

            // 스트림 전송 가능
            responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("fromStream 1st Response").build());
            responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("fromStream 2nd Response").build());
            responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("fromStream 3rd Response").build());
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<gRPCSample.Request> biStream(final StreamObserver<gRPCSample.Response> responseObserver) {

            // 스트림 전송 가능
            responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("biStream 1st Response").build());
            responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("biStream 2nd Response").build());
            // 스트림 수신
            return new StreamObserver<gRPCSample.Request>(){
                int streamCount = 0;

                @Override
                public void onNext(gRPCSample.Request value) {
                    String reqCmd = value.getCmd();
                    System.out.println("biStream request 수신 : " + reqCmd);
                    responseObserver.onNext(gRPCSample.Response.newBuilder().setCmd("res to " + reqCmd).build());
                    streamCount++;

                    if(streamCount > 3){
                        responseObserver.onCompleted();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("biStream 연결 에러" + t);
                }

                @Override
                public void onCompleted() {
                    System.out.println("biStream 연결 종료");
                }
            };
        }
    }
}
