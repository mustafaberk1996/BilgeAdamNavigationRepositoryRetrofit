package com.example.navigationwithtoolbartitlelesson.ui.addPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.navigationwithtoolbartitlelesson.R
import com.example.navigationwithtoolbartitlelesson.data.state.CreatePostState
import com.example.navigationwithtoolbartitlelesson.databinding.FragmentAddPostBinding
import com.example.navigationwithtoolbartitlelesson.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddPostFragment : Fragment(R.layout.fragment_add_post) {


    private lateinit var binding:FragmentAddPostBinding
    private val viewModel:AddPostViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddPostBinding.bind(view)

        observeCreatePostState()

        binding.includeAddOrUpdatePost.btnSave.setOnClickListener {
            viewModel.createPost(binding.includeAddOrUpdatePost.etTitle.text.toString().trim(), binding.includeAddOrUpdatePost.etBody.text.toString().trim())
        }
    }

    private fun observeCreatePostState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.createPostState.collect{
                    when(it){
                        is CreatePostState.Idle->{}
                        is CreatePostState.Loading->{
                            binding.includeAddOrUpdatePost.progressBar.isVisible = true
                        }
                        is CreatePostState.Success->{
                            binding.includeAddOrUpdatePost.progressBar.isVisible = false
                            requireContext().showToast("kayit basarili")
                        }
                        is CreatePostState.Error->{
                            binding.includeAddOrUpdatePost.progressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }

}