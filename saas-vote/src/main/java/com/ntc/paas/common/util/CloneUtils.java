package com.ntc.paas.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 克隆方法
 * 
 * @author allen.yuan
 * @date 2018年8月2日 下午5:29:24
 * @version 中设E-电梯平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class CloneUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) {

		T clonedObj = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			clonedObj = (T) ois.readObject();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clonedObj;
	}
}
