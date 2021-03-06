package com.hello.test.learnandroid.opengles;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.test.learnandroid.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleOpenGL extends AppCompatActivity {
  private GLSurfaceView glSurfaceView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    glSurfaceView = new CustomGLSurfaceView(this);
    setContentView(glSurfaceView);
  }

  class CustomGLSurfaceView extends GLSurfaceView{
    private final GLRenderer glRenderer;

    public CustomGLSurfaceView(Context context) {
      super(context);
      setEGLContextClientVersion(2);
      glRenderer = new GLRenderer();
      setRenderer(glRenderer);
    }
  }

  class GLRenderer implements GLSurfaceView.Renderer{

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
      GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
      GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
      GLES20.glViewport(0,0,width,height);
    }
  }
}
