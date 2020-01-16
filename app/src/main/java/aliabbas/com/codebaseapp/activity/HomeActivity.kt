package aliabbas.com.codebaseapp.activity

import aliabbas.com.codebaseapp.R
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity @Inject constructor() : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
