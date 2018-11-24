package ar.tis.tisar

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3

class TranslatableNode : Node() {


  fun addOffset(x: Float = 0F, y: Float = 0F, z: Float = 0F) {
    val posX = localPosition.x + x
    val posY = localPosition.y + y
    val posZ = localPosition.z + z

    localPosition = Vector3(posX, posY, posZ)
  }

  fun addRot(x: Float, y: Float, z: Float) {
    val q1 = this.getLocalRotation()
    val q2 = Quaternion.axisAngle(Vector3(1f, 0f, 0f), x)
    val q3 = Quaternion.axisAngle(Vector3(0f, 1f, 0f), y)
    val q4 = Quaternion.axisAngle(Vector3(0f, 0f, 1f), z)
    this.setLocalRotation(Quaternion.multiply(Quaternion.multiply(Quaternion.multiply(q1, q2),q3),q4))
  }
}