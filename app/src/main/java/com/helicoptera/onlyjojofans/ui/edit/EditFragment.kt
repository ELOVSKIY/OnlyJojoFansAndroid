package com.helicoptera.onlyjojofans.ui.edit

import android.app.Activity
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
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository
import com.helicoptera.onlyjojofans.databinding.EditFragmentBinding
import com.helicoptera.onlyjojofans.ui.registration.recycler.ImageAdapter
import net.alhazmy13.mediapicker.Image.ImagePicker
import net.alhazmy13.mediapicker.Video.VideoPicker

class EditFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        val binding: EditFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.edit_fragment, container, false)
        viewModel.initializeViewModel(CharacterRepository.currentCharacter!!)
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
        viewModel.navigateToDetail.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.onNavigateTODetail()
                findNavController().navigate(R.id.action_editFragment_to_detailFragment)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
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