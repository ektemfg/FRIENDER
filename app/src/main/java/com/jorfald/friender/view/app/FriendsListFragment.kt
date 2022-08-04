package com.jorfald.friender.view.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.friender.R
import com.jorfald.friender.viewModel.FriendsViewModel
import com.template.androidtemplate.utils.FriendsDataObserver

class FriendsListFragment : Fragment() {
    private val viewModel: FriendsViewModel by activityViewModels()
    private lateinit var customAdapter: FriendsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var customLayoutManager: LinearLayoutManager
    private lateinit var friends_backButton: Button
    private lateinit var friends_refreshButton: Button
    private lateinit var noFriends: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customAdapter = FriendsAdapter()
        viewModel.fetchFriendsValues()
        return inflater.inflate(R.layout.friends_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friends_backButton = view.findViewById(R.id.friends_back_button)
noFriends = view.findViewById(R.id.noFriendsView)
        friends_refreshButton = view.findViewById(R.id.friends_list_refresh)
        bindObservers()
        bindButtons()
        recyclerView = view.findViewById(R.id.friends_recycler)
        customLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = customLayoutManager

        recyclerView.adapter = customAdapter
        val hasFriendsObserver = FriendsDataObserver(view, recyclerView, noFriends)
        customAdapter.registerAdapterDataObserver(hasFriendsObserver)

        }

    private fun bindObservers() {
        viewModel.savedFriends.observe(viewLifecycleOwner) { friends ->
            customAdapter.updateData(friends)
        }
    }

    private fun bindButtons() {
        friends_backButton.setOnClickListener {
            findNavController().navigate(com.jorfald.friender.view.app.FriendsListFragmentDirections.actionFriendsListFragmentToFriendsFragment2())
        }
        friends_refreshButton.setOnClickListener{
            viewModel.fetchFriendsValues()
            viewModel.toast("Refreshed")
        }
    }


}

