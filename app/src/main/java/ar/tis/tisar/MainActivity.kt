package ar.tis.tisar

import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import ar.tis.tisar.helper.ARLocationPermissionHelper
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.fragment_ar.*


class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment

    private var textViewRenderable: ViewRenderable? = null
    private lateinit var displayTextView: DisplayTextView

    private var textViewRenderableB: ViewRenderable? = null
    private lateinit var displayTextViewB: DisplayTextView

    var x1: Float = 0.0f
    var x2: Float = 0.0f
    var y1: Float = 0.0f
    var y2: Float = 0.0f

    private var isTracking: Boolean = false
    private var isHitting: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ar)
        ARLocationPermissionHelper.requestPermission(this)

        arFragment = ux_fragment as ArFragment

        initResources()

        // Adds a listener to the ARSceneView
        // Called before processing each frame
        arFragment.arSceneView.scene.addOnUpdateListener { frameTime ->
            arFragment.onUpdate(frameTime)
            onUpdate()
        }


        floatingButtonLeft.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        floatingButtonRight.setOnClickListener{
            val intent = Intent(this@MainActivity, ConstructionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // Set the onclick lister for our button
        // Change this string to point to the .sfb file of your choice :)
        floatingActionButton.setOnClickListener { addObject(Uri.parse("fox.sfb")) }
        showFab(false)
    }

    override fun onTouchEvent(touchEvent:MotionEvent):Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
                y1 = touchEvent.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                y2 = touchEvent.y
                if (x1 < x2) {
                    val i = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(i)
                }
                // Navigation zur Informationsseite
                if (x1 > x2) {
                    val i = Intent(this@MainActivity, ConstructionActivity::class.java)
                    startActivity(i)
                }
            }
        }
        return false
    }

    // Simple function to show/hide our FAB
    private fun showFab(enabled: Boolean) {
        if (enabled) {
            floatingActionButton.isEnabled = true
            floatingActionButton.visibility = View.VISIBLE
        } else {
            floatingActionButton.isEnabled = false
            floatingActionButton.visibility = View.GONE
        }
    }


    // Updates the tracking state
    private fun onUpdate() {
        updateTracking()
        // Check if the devices gaze is hitting a plane detected by ARCore
        if (isTracking) {
            val hitTestChanged = updateHitTest()
            if (hitTestChanged) {
                showFab(isHitting)
            }
        }

    }

    // Performs frame.HitTest and returns if a hit is detected
    private fun updateHitTest(): Boolean {
        val frame = arFragment.arSceneView.arFrame
        val point = getScreenCenter()
        val hits: List<HitResult>
        val wasHitting = isHitting
        isHitting = false
        if (frame != null) {
            hits = frame.hitTest(point.x.toFloat(), point.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane && trackable.isPoseInPolygon(hit.hitPose)) {
                    isHitting = true
                    break
                }
            }
        }
        return wasHitting != isHitting
    }

    // Makes use of ARCore's camera state and returns true if the tracking state has changed
    private fun updateTracking(): Boolean {
        val frame = arFragment.arSceneView.arFrame
        val wasTracking = isTracking
        isTracking = frame.camera.trackingState == TrackingState.TRACKING
        return isTracking != wasTracking
    }

    // Simply returns the center of the screen
    private fun getScreenCenter(): Point {
        val view = findViewById<View>(android.R.id.content)
        return Point(view.width / 2, view.height / 2)
    }

    /**
     * @param model The Uri of our 3D sfb file
     *
     * This method takes in our 3D model and performs a hit test to determine where to place it
     */
    private fun addObject(model: Uri) {
        val frame = arFragment.arSceneView.arFrame
        val point = getScreenCenter()
        if (frame != null) {
            val hits = frame.hitTest(point.x.toFloat(), point.y.toFloat())
            for (hit in hits) {
                val trackable = hit.trackable
                if (trackable is Plane && trackable.isPoseInPolygon(hit.hitPose)) {
                    placeObject(arFragment, hit.createAnchor(), model)
                    break
                }
            }
        }
    }

    /**
     * @param fragment our fragment
     * @param anchor ARCore anchor from the hit test
     * @param model our 3D model of choice
     *
     * Uses the ARCore anchor from the hitTest result and builds the Sceneform nodes.
     * It starts the asynchronous loading of the 3D model using the ModelRenderable builder.
     */


    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Uri) {
        val spacing = 0.419F
        val anchorNode = AnchorNode(anchor)

        anchorNode.setParent(arFragment.arSceneView.scene)

        // Add the scoreboard view to the plane
        val renderableView = textViewRenderable ?: return
        TranslatableNode()
            .also {
                it.setParent(anchorNode)
                it.renderable = renderableView
                it.addOffset(x = -spacing, y = 1.2F)
                it.addRot(0f,60.0f,0f)
            }
        val renderableView2 = textViewRenderable ?: return
        TranslatableNode()
            .also {
                it.setParent(anchorNode)
                it.renderable = renderableView2
                it.addOffset(x = spacing, y = 1.2F)
                it.addRot(0f,-60.0f,0f)
            }
        val renderableView3 = textViewRenderable ?: return
        TranslatableNode()
            .also {
                it.setParent(anchorNode)
                it.renderable = renderableView3
                it.addOffset(x = 0f, y = 1.2F, z=.726f)
                it.addRot(0f,0f,0f)
            }

        ModelRenderable.builder().setSource(fragment.context,model).build().thenAccept{
            addNodeToScene(fragment,anchor,it)
        }
        /*val renderableView1b = textViewRenderableB ?: return
        TranslatableNode()
            .also {
                it.setParent(anchor)
                it.renderable = renderableView1b
                it.addOffset(x = -spacing+0.001F, y = .5F)
                it.addRot(0f,67.5f,0f)
            }
        val renderableView2b = textViewRenderableB ?: return
        TranslatableNode()
            .also {
                it.setParent(anchor)
                it.renderable = renderableView2b
                it.addOffset(x = spacing-0.001F, y = .5F)
                it.addRot(0f,-67.5f,0f)
            }*/
    }

    /**
     * @param fragment our fragment
     * @param anchor ARCore anchor
     * @param renderable our model created as a Sceneform Renderable
     *
     * This method builds two nodes and attaches them to our scene
     * The Anchor nodes is positioned based on the pose of an ARCore Anchor. They stay positioned in the sample place relative to the real world.
     * The Transformable node is our Model
     * Once the nodes are connected we select the TransformableNode so it is available for interactions
     */
    private fun addNodeToScene(fragment: ArFragment, anchor: Anchor, renderable: ModelRenderable) {
        val anchorNode = AnchorNode(anchor)
        // TransformableNode means the user to move, scale and rotate the model
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.renderable = renderable
        transformableNode.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
    }

    fun navToLogin(v: View) {
        Log.e("TAG", "ONCLICK")
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun initResources() {
        // Create a droid renderable (asynchronous operation,
        // result is delivered to `thenAccept` method)

        displayTextView = DisplayTextView(this)
        displayTextViewB = DisplayTextView(this)

        displayTextView.setText("Erneuerung der Gasleitung")
        displayTextViewB.setText("                                           ")

        // create a scoreboard renderable (asynchronous operation,
        // result is delivered to `thenAccept` method)
        ViewRenderable.builder()
            .setView(this, displayTextView)
            .build()
            .thenAccept {
                textViewRenderable = it
            }

        ViewRenderable.builder()
            .setView(this, displayTextViewB)
            .build()
            .thenAccept {
                textViewRenderableB = it
            }


    }

}
