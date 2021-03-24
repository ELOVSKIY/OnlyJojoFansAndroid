package com.helicoptera.onlyjojofans.ui.registration

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.helicoptera.onlyjojofans.R
import com.helicoptera.onlyjojofans.databinding.RegistrationFragmentBinding
import net.alhazmy13.mediapicker.Image.ImagePicker

import android.app.Activity.RESULT_OK
import com.helicoptera.onlyjojofans.ui.registration.recycler.ImageAdapter
import net.alhazmy13.mediapicker.Video.VideoPicker

class RegistrationFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        val binding: RegistrationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        imageAdapter = ImageAdapter()
        imageAdapter.setOnClickListener {
            viewModel.removeImage(it)
        }
        binding.recycler.adapter = imageAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.navigateToList.observe(this.viewLifecycleOwner) {
            if (it) {
                viewModel.onNavigateToList()
                findNavController().navigate(R.id.action_registationFragment_to_listFragment)
            }
        }
        viewModel.chooseImage.observe(this.viewLifecycleOwner) {
            if (it) {
                viewModel.onLoadImage()
                ImagePicker.Builder(activity)
                    .mode(ImagePicker.Mode.GALLERY)
                    .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                    .directory(ImagePicker.Directory.DEFAULT)
                    .extension(ImagePicker.Extension.PNG)
                    .scale(600, 600)
                    .allowMultipleImages(false)
                    .enableDebuggingMode(true)
                    .build()
            }
        }
        viewModel.chooseVideo.observe(this.viewLifecycleOwner) {
            if (it) {
                viewModel.onLoadVideo()
                VideoPicker.Builder(activity)
                    .mode(VideoPicker.Mode.GALLERY)
                    .directory(VideoPicker.Directory.DEFAULT)
                    .extension(VideoPicker.Extension.MP4)
                    .enableDebuggingMode(true)
                    .build()
            }
        }
        viewModel.images.observe(this.viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                imageAdapter.submitList(it)
                imageAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                val mPaths: ArrayList<String>? =
                    data?.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH)
                val mediaPaths = ArrayList<String>(mPaths)
                viewModel.onImagesReceive(mediaPaths)
            } else if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE) {
                val mPaths: ArrayList<String>? =
                    data?.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH)
                val mediaPaths = ArrayList<String>(mPaths)
                viewModel.onVideoReceive(mediaPaths)
            }
        }
    }
}