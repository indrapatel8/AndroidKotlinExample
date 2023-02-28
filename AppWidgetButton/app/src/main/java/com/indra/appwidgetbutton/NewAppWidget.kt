package com.indra.appwidgetbutton

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

class NewAppWidget : AppWidgetProvider() {
    private val MyOnClick: String = "myOnClickTag"
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent) {
        super.onReceive(context, intent) //add this line
        if (MyOnClick == intent.action) {
            Toast.makeText(context, "Have avdi gayu che", Toast.LENGTH_SHORT).show()
        }
    }

    internal fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)
      //  views.setTextViewText(R.id.appwidget_text, context.getString(R.string.appwidget_text))

        val intent = Intent(context, javaClass)
        intent.action = MyOnClick
        views.setOnClickPendingIntent(R.id.btnWidget,
            PendingIntent.getBroadcast(context,0,intent,0)
        )
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}