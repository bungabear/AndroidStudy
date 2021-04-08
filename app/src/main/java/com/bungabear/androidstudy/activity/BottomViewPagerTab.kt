package com.bungabear.androidstudy.activity

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bungabear.androidstudy.R
import kotlinx.android.synthetic.main.layout_tab_item.view.*

class BottomViewPagerTab : AppCompatActivity() {
    companion object{
        private val TAG = BottomViewPagerTab::class.java.simpleName
        private val colorList = listOf(0xFFFF0000.toInt(), 0xFF0000FF.toInt(), 0xFF00FF00.toInt(), 0xFFFFFFFF.toInt())
    }

    lateinit var viewPager : androidx.viewpager.widget.ViewPager
    lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_viewpager_tab)
        viewPager = findViewById(R.id.vp_activity_bottom_viewpager_tab)
        tabLayout = findViewById(R.id.tl_activity_bottom_viewpager_tab)

//        val tab0 = makeTab(layoutInflater, "탭0", R.drawable.ic_sample_tab)
//        val tab1 = makeTab(layoutInflater, "탭1", R.drawable.ic_sample_tab)
//        val tab2 = makeTab(layoutInflater, "탭2", R.drawable.ic_sample_tab)
//        val tab3 = makeTab(layoutInflater, "탭3", R.drawable.ic_sample_tab)
        tabLayout.removeAllTabs()

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())

        tabLayout.getTabAt(0)?.customView = makeTab(layoutInflater, "탭0", R.drawable.ic_sample_tab)
        tabLayout.getTabAt(1)?.customView = makeTab(layoutInflater, "탭1", R.drawable.ic_sample_tab)
        tabLayout.getTabAt(2)?.customView = makeTab(layoutInflater, "탭2", R.drawable.ic_sample_tab)
        tabLayout.getTabAt(3)?.customView = makeTab(layoutInflater, "탭3", R.drawable.ic_sample_tab)

        viewPager.adapter = TestPagerAdapter(supportFragmentManager)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab) {
                p0.customView?.iv_tab?.isSelected = true
                p0.customView?.tv_tab?.isSelected = true
            }
            override fun onTabUnselected(p0: TabLayout.Tab) {
                p0.customView?.iv_tab?.isSelected = false
                p0.customView?.tv_tab?.isSelected = false
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                viewPager.currentItem = p0.position
                p0.customView?.iv_tab?.isSelected = true
            }

        })
        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                Log.d(TAG, "position : $position offset : $positionOffset ")
                if(position != colorList.lastIndex){
                    tabLayout.setSelectedTabIndicatorColor(ColorUtils.blendARGB(colorList[position], colorList[position+1], positionOffset))
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })

    }

    private fun makeTab(inflater: LayoutInflater, text : String, icon : Int) : View{
        val tabLayout = inflater.inflate(R.layout.layout_tab_item, null, false)
        tabLayout.iv_tab.setImageResource(icon)
        tabLayout.tv_tab.text = text
//        tabLayout.tv_tab.setTextColor(ResourcesCompat.getColorStateList(resources, textColor, null))
        return tabLayout
    }

    class TestPagerAdapter(fm : androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm){
        private val fragments = ArrayList<ColorFragment>()
        init {
            fragments.add(ColorFragment.newInstance(colorList[0]))
            fragments.add(ColorFragment.newInstance(colorList[1]))
            fragments.add(ColorFragment.newInstance(colorList[2]))
            fragments.add(ColorFragment.newInstance(colorList[3]))
        }

        override fun getItem(position: Int) = fragments[position]
        override fun getCount() = fragments.size
    }

    // simple color background fragment
    class ColorFragment : androidx.fragment.app.Fragment(){
        private var backgroundColor : Int = 0xFFFFFFFF.toInt()
        private lateinit var mContext : Context
        private lateinit var rootView : ConstraintLayout

        companion object{
            fun newInstance(color : Int) : ColorFragment{
                val fragment = ColorFragment()
                fragment.backgroundColor = color
                return fragment
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val root = ConstraintLayout(mContext)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            root.layoutParams = params
            rootView = root
            return root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            rootView.setBackgroundColor(backgroundColor)
        }

        override fun onAttach(context: Context) {
            mContext = context
            super.onAttach(context)
        }
    }
}