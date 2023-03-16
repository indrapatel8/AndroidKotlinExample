package com.indra.widgetdemotoday

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {
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

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {

        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent != null) {
            if (intent.action == "ONE") {
                Toast.makeText(context,
                    "BUTTON 111111", Toast.LENGTH_LONG).show()
            }
            else if (intent.action == "TWO") {
                Toast.makeText(context,
                    "BUTTON 2222", Toast.LENGTH_LONG).show()
            }
        }
    }

    internal fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)

        val intent = Intent(context,javaClass)
        intent.action = "ONE"
        views.setOnClickPendingIntent(
            R.id.appButton1,
            PendingIntent.getBroadcast(context, 0, intent, 0)
        )

        val intent2 =  Intent(context,javaClass)
        intent2.action = "TWO"
        views.setOnClickPendingIntent(
            R.id.appButton2,
            PendingIntent.getBroadcast(context, 0, intent2, 0)
        )
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}