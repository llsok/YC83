package com.yc.lib.util;

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

}
