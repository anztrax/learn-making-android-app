package com.hello.test.learnandroid.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.test.learnandroid.R;
import com.hello.test.learnandroid.opengles.openglshape.ProjectionTriangle;
import com.hello.test.learnandroid.opengles.openglshape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ProjectionAndCameraActivity extends AppCompatActivity {

  //they use OpenGL Shading Language (GLSL)
  private GLSurfaceView glSurfaceView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    glSurfaceView = new ProjectionAndCameraActivity.CustomGLSurfaceView(this);
    setContentView(glSurfaceView);
  }

  class CustomGLSurfaceView extends GLSurfaceView {
    private final ProjectionAndCameraActivity.GLRenderer glRenderer;

    public CustomGLSurfaceView(Context context) {
      super(context);
      setEGLContextClientVersion(2);
      glRenderer = new ProjectionAndCameraActivity.GLRenderer();
      setRenderer(glRenderer);
    }
  }

  class GLRenderer implements GLSurfaceView.Renderer{
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

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
      projectionTriangle.draw(mMVPMatrix);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
      GLES20.glViewport(0,0,width,height);
      float ratio = (float)width/height;
      Matrix.frustumM(mProjectionMatrix,0,-ratio,ratio,-1,1,3,7);
    }
  }
}
