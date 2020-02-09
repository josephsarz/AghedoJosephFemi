package codegene.femicodes.aghedojosephfemi.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import codegene.femicodes.aghedojosephfemi.R
import codegene.femicodes.aghedojosephfemi.ui.utils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onStart() {
        super.onStart()
        (activity as? MainActivity)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as? MainActivity)?.supportActionBar?.show()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_READ_EXTERNAL_STORAGE
            )
        } else {
            proceedToTheApp()
        }
    }

    private fun proceedToTheApp() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFilterListFragment())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    proceedToTheApp()
                } else {
                    (activity as? MainActivity)?.onBackPressed()
                }
                return
            }
        }
    }

}