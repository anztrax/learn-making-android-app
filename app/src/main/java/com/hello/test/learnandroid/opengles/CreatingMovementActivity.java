package com.hello.test.learnandroid.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.opengles.openglshape.ProjectionTriangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CreatingMovementActivity extends AppCompatActivity {

  //they use OpenGL Shading Language (GLSL)
  private GLSurfaceView glSurfaceView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    glSurfaceView = new CreatingMovementActivity.CustomGLSurfaceView(this);
    setContentView(glSurfaceView);
  }

  class CustomGLSurfaceView extends GLSurfaceView {
    private final CreatingMovementActivity.GLRenderer glRenderer;

    public CustomGLSurfaceView(Context context) {
      super(context);
      setEGLContextClientVersion(2);
      glRenderer = new CreatingMovementActivity.GLRenderer();
      setRenderer(glRenderer);
    }
  }

  class GLRenderer implements GLSurfaceView.Renderer{
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];

    private ProjectionTriangle projectionTriangle;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
      projectionTriangle = new ProjectionTriangle();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
      Matrix.setLookAtM(mViewMatrix,0,0,0,-3,0f, 0f, 0f, 0f,1.0f, 0.0f);
      Matrix.multiplyMM(mMVPMatrix,0,mProjectionMatrix,0,mViewMatrix,0);
      GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
      float[] tempMatrix = new float[16];
      long time  = SystemClock.uptimeMillis() % 4000L;  //calculate system update
      float angle = 0.090f * ((int)time);
      Matrix.setRotateM(mRotationMatrix,0,angle,0,0,-1.0f);
      Matrix.multiplyMM(tempMatrix,0,mMVPMatrix,0,mRotationMatrix,0);
      projectionTriangle.draw(tempMatrix);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
      GLES20.glViewport(0,0,width,height);
      float ratio = (float)width/height;
      Matrix.frustumM(mProjectionMatrix,0,-ratio,ratio,-1,1,3,7);
    }
  }
}
