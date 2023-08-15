package com.example.navigationwithtoolbartitlelesson.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.navigationwithtoolbartitlelesson.R
import com.example.navigationwithtoolbartitlelesson.data.state.PostListState
import com.example.navigationwithtoolbartitlelesson.databinding.FragmentPostsBinding
import com.example.navigationwithtoolbartitlelesson.ui.adapter.PostsAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PostsFragment : Fragment(R.layout.fragment_posts) {


    lateinit var binding:FragmentPostsBinding

    private val viewModel:PostsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPostsBinding.bind(view)

        viewModel.getAllPosts()

        initListener()

        observePostListState()





    }

    private fun observePostListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.postListState.collect{
                    when(it){
                        is PostListState.Idle->{}
                        is PostListState.Loading->{}
                        is PostListState.Result->{
                            binding.rvPosts.adapter = PostsAdapter(requireContext(), it.posts) {post->
                                val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(post.title, post.id)
                                findNavController().navigate(action)
                            }
                        }
                        is PostListState.Error->{}
                    }
                }
            }
        }
    }

    private fun initListener() {
        binding.btnPostDetail.setOnClickListener {
            val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment("Mustafa",123)
            findNavController().navigate(action)
        }

        binding.btnAddPost.setOnClickListener {
            findNavController().navigate(R.id.action_postsFragment_to_addPostFragment)
        }

        binding.btnDrawer.setOnClickListener {
            findNavController().navigate(R.id.action_postsFragment_to_drawerActivity)
        }
    }

}