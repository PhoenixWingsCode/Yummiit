package com.example.yummiit.homeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.yummiit.R

class MenFragment : Fragment() {

    private lateinit var imageSlider: ImageSlider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_men, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageSlider = view.findViewById(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()


        imageList.add(SlideModel("android.resource://" + requireActivity().packageName + "/"+R.drawable.men_image1,""))
        imageList.add(SlideModel("android.resource://"+requireActivity().packageName+"/"+R.drawable.men_image2,""))
        imageList.add(SlideModel("android.resource://"+requireActivity().packageName+"/"+R.drawable.men_image3,""))

        imageSlider.setImageList(imageList,ScaleTypes.FIT)
    }
}