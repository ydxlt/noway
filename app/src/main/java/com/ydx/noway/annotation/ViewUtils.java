package com.ydx.noway.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ViewUtils {

	public static void inject(Object handler,View view) {
		injectObject(handler, new ViewFinder(view));
	}

	public static void inject(Activity activity) {
		injectObject(activity, new ViewFinder(activity));
	}

	/**
	 * java反射机制中获取类handlerType中的方法
	 * @param String name 这个是方法名
	 * @param Class<?> parameterType 这个是方法对应的参数类型
	 * @description
	 */
 public static void injectObject(Object handler, ViewFinder finder) {
		Class<?> handlerType = handler.getClass();
		//实现类注解 绑定view
		ContentView contentView = handlerType.getAnnotation(ContentView.class);
		if (contentView != null) {
			try {
			Method setContentViewMethod = handlerType.getMethod("setContentView", int.class);
			setContentViewMethod.invoke(handler, contentView.value());
			} catch (Throwable e) {
		}
	}

		Field[] fields = handlerType.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				ViewInject viewInject = field.getAnnotation(ViewInject.class);
				if (viewInject != null) {
					try {
						View view = finder.findViewById(viewInject.value(),
								viewInject.parentId());
						if (view != null) {
							field.setAccessible(true);
							field.set(handler, view);
						}
					} catch (Throwable e) {
					}
				}
			}
		}
	}
}
