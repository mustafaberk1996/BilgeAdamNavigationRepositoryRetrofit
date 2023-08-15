package com.example.navigationwithtoolbartitlelesson.ui.updatePost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.navigationwithtoolbartitlelesson.R
import com.example.navigationwithtoolbartitlelesson.databinding.FragmentUpdatePostBinding
import com.example.navigationwithtoolbartitlelesson.showToast
import kotlinx.coroutines.launch

class UpdatePostFragment : Fragment(R.layout.fragment_update_post) {

    private lateinit var binding: FragmentUpdatePostBinding
    val args: UpdatePostFragmentArgs by navArgs()
    private val viewModel:UpdatePostViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUpdatePostBinding.bind(view)

        viewModel.getPost(args.postId)


        observePost()
        observeUpdatePost()


        binding.includeAddOrUpdatePost.btnSave.text = "Update"
        binding.includeAddOrUpdatePost.btnSave.setOnClickListener {
            viewModel.update(binding.includeAddOrUpdatePost.etBody.text.toString(), binding.includeAddOrUpdatePost.etTitle.text.toString())
        }


    }

    private fun observeUpdatePost() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.postUpdate.collect{
                    if (it) requireContext().showToast("post updated")
                    else requireContext().showToast("post isnot updated")
                }
            }
        }
    }

    private fun observePost() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.post.collect{
                    it?.let {
                        binding.includeAddOrUpdatePost.etBody.setText(it.body)
                        binding.includeAddOrUpdatePost.etTitle.setText(it.title)
                    }
                }
            }
        }
    }
}