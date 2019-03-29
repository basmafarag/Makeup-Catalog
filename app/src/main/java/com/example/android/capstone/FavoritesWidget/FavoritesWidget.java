package com.example.android.capstone.FavoritesWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;
import com.example.android.capstone.UI.FavoritesListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesWidget extends AppWidgetProvider {

    static List<Product> productList=new ArrayList<>();
    private static String text;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorites_widget);
        views.setTextViewText(R.id.appwidget_text, text);

        Intent intent = new Intent(context, FavoritesListActivity.class);
        //intent.putExtra("myFavorites", (Serializable) productList);
        views.setRemoteAdapter(R.id.lv_favorites_widget, intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("widgettttt   ", "recieved");

        if (intent.hasExtra(String.valueOf(R.string.myFavorites))) {
            Log.d("widgettttt   ", "recieved iffffff");

            //text = recipe.getName();

            productList = (List<Product>) intent.getSerializableExtra(String.valueOf(R.string.myFavorites));
            Log.d("widgetttttbasma   ", ""+String.valueOf(productList));

            for(Product ing : productList)
            {
                text += ing.getName();
                text += "\n";
            }
        } else {
            Log.d("widgettttt   ", ""+"elsepart");

        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName thisWidget = new ComponentName(context.getApplicationContext(), FavoritesWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_favorites_widget);
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }

        super.onReceive(context, intent);
    }
}

