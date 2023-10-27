package com.example.fidofriendsidebar.ui.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fidofriendsidebar.databinding.FragmentSlideshowBinding

class AppointmentsFragment : Fragment() {
    private val appointmentsViewModel: AppointmentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.recyclerView

        // Configura el RecyclerView y el adaptador
        val meetingsAdapter = MeetingsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = meetingsAdapter

        // Observa los cambios en la lista de reuniones y actualiza el adaptador
        appointmentsViewModel.meetings.observe(viewLifecycleOwner) { meetings ->
            meetingsAdapter.submitList(meetings)
        }

        return root
    }
}
