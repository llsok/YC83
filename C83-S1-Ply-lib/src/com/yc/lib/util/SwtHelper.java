package com.yc.lib.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SwtHelper {
	
	/**
	 * 	显示消息提示框
	 * @param shell
	 * @param msg
	 */
	public static void msg(Shell shell, String msg) {
		MessageBox mb = new MessageBox(shell);
		mb.setText("系统提示");
		mb.setMessage(msg);
		mb.open();
	}
	
	/**
	 * 	显示提问框, 按是按钮, 返回 true
	 * @param shell
	 * @param msg
	 * @return
	 */
	public static boolean confirm(Shell shell, String msg) {
		MessageBox mb = new MessageBox(shell,SWT.YES | SWT.NO);
		mb.setText("系统提示");
		mb.setMessage(msg);
		return mb.open() == SWT.YES;
	}

}
