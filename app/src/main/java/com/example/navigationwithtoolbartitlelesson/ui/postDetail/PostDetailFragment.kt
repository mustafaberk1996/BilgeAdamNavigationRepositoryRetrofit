package com.example.navigationwithtoolbartitlelesson.ui.postDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationwithtoolbartitlelesson.R
import com.example.navigationwithtoolbartitlelesson.data.state.PostDeleteState
import com.example.navigationwithtoolbartitlelesson.data.state.PostDetailState
import com.example.navigationwithtoolbartitlelesson.databinding.FragmentPostDetailBinding
import kotlinx.coroutines.launch


class PostDetailFragment : Fragment(R.layout.fragment_post_detail) {

    private lateinit var binding:FragmentPostDetailBinding
    private val viewModel:PostDetailViewModel by activityViewModels()
    val args: PostDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostDetailBinding.bind(view)




        viewModel.getPostById(args.id)

        initListeners()
        observePost()
        observeDelete()




    }

    private fun observeDelete() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postDeleteState.collect {
                    println("postDeleteState --> $it")
                    when(it){
                        is PostDeleteState.Idle->{}
                        is PostDeleteState.Loading->{
                            binding.progressBar.isVisible = true
                        }
                        is PostDeleteState.Success->{
                            binding.progressBar.isVisible = false
                            findNavController().navigate(R.id.action_posts_fragment)
                            Toast.makeText(requireContext(),"post deleted!",Toast.LENGTH_LONG).show()
                        }
                        is PostDeleteState.Error->{
                            binding.progressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.ivDelete.setOnClickListener {
            viewModel.delete(args.id)
        }
    }

    private fun observePost() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postDetailState.collect {
                    when(it){
                        is PostDetailState.Idle->{}
                        is PostDetailState.Loading->{
                            binding.progressBar.isVisible = true
                        }
                        is PostDetailState.PostNotFound->{
                            Toast.makeText(requireContext(),"post not found",Toast.LENGTH_LONG).show()
                        }
                        is PostDetailState.Success->{
                            binding.progressBar.isVisible = false
                            binding.tvTitle.text = it.post.title
                            binding.tvBody.text = it.post.body
                        }
                        is PostDetailState.Error->{
                            binding.progressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }

}