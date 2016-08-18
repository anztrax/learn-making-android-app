package com.hello.test.learnandroid.appwidget;

import android.app.PendingIntent;
import android.appwidget.*;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.hello.test.learnandroid.R;

/**
 * Created by andrew on 17/08/16.
 */
public class HomeScreenWidgetProvider extends AppWidgetProvider{
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);
    for(int count=0; count < appWidgetIds.length; count++){
      RemoteViews appWidgetLayout = new RemoteViews(context.getPackageName(), R.layout.appwidget);
      Intent intent = new Intent(context,HomeScreenWidgetProvider.class);
      PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
      appWidgetLayout.setOnClickPendingIntent(R.id.analogClock,pendingIntent);
      appWidgetManager.updateAppWidget(appWidgetIds[count],appWidgetLayout);
    }
  }
}
