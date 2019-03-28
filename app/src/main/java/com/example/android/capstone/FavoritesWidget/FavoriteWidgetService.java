package com.example.android.capstone.FavoritesWidget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.capstone.Model.Product;
import com.example.android.capstone.R;
import java.util.List;

public class FavoriteWidgetService extends RemoteViewsService {
    private List<Product> productList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (RemoteViewsFactory) new RemoteListViewsFactory(getApplicationContext());
    }

    class RemoteListViewsFactory implements FavoriteWidgetService.RemoteViewsFactory {

        final Context mContext;

        RemoteListViewsFactory(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            productList = FavoritesWidget.productList;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (productList == null) return 0;
            return productList.size();
        }

        @Override
        public RemoteViews getViewAt(int index) {
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.favorite_item_widget);
            Product product = productList.get(index);
            int position = index + 1;
            String widgetItem = String.format(getString(R.string.widget_item_format), position,
                    product.getName());
            views.setTextViewText(R.id.tv_favorite_widget_item, widgetItem);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}

