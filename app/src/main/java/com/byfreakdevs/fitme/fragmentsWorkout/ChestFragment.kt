package com.byfreakdevs.fitme.fragmentsWorkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.FragmentChestBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class ChestFragment : Fragment() {

    private lateinit var binding: FragmentChestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChestBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayerView1
        lifecycle.addObserver(youTubePlayerView)
        viewLifecycleOwner.lifecycle.addObserver(youTubePlayerView)


    }



}