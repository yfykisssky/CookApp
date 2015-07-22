package com.cookapp.cookapp.tools;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.graphics.Bitmap;
import android.os.Handler;

public class AsyncImageLoader {

	//为了加快速度，在内存中开启缓存（主要应用于重复图片较多时，或者同一个图片要多次被访问，比如在ListView时来回滚动）

	public Map<String,SoftReference<Bitmap>> imageCache = new HashMap<String,SoftReference<Bitmap>>();

	//固定五个线程来执行任务
	
	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	private final Handler handler = new Handler();

	public Bitmap DrawableloadImage(final String imageUrl,final String filePath,final ImageCallback callback) {

		//如果缓存过就从缓存中取出数据

		if (imageCache.containsKey(imageUrl))
		{

			SoftReference<Bitmap> softReference = imageCache.get(imageUrl);

			if (softReference.get()
					!= null)
			{

				return softReference.get();

			}

		}

		//缓存中没有图像，则从网络上取出数据，并将取出的数据缓存到内存中

		executorService.submit(new Runnable()
		{

			public void run()
			{

				try {

					final Bitmap bitmap =GetFileHelper.getImageBitmap(imageUrl,filePath,null); 

					imageCache.put(imageUrl,new SoftReference<Bitmap>(bitmap));

					handler.post(new Runnable()
					{

						public void run()
						{

							callback.imageLoaded(bitmap);

						}

					});

				}
				catch (Exception e) {

					throw new RuntimeException(e);

				}

			}

		});

		return null;

	}

	//对外界开放的回调接口

	public interface ImageCallback
	{

		public void imageLoaded(Bitmap bitmap);

	}
}

