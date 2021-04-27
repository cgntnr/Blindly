package ch.epfl.sdp.blindly.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.epfl.sdp.blindly.R
import ch.epfl.sdp.blindly.UserMapActivity
import com.google.android.gms.maps.model.LatLng


class MapFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "messageArgs"
        private var counter: Int? = null

        fun newInstance(counter: Int): MessagePageFragment {
            val fragment = MessagePageFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            counter = requireArguments().getInt(ARG_COUNT)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map_page, container, false)
    }
    fun startUserMap(view: View) {
        val intent = Intent(activity, UserMapActivity::class.java)
        val points = arrayOf(LatLng(-33.0, 151.0))
        intent.putExtra(UserMapActivity.POINTS, points)
        startActivity(intent)
    }
}