package com.yc.lib.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.yc.lib.biz.BizException;
import com.yc.lib.biz.UserBiz;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginWin {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWin window = new LoginWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(369, 260);
		shell.setText("登录窗口");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(67, 52, 49, 17);
		label.setText("用户名:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(123, 49, 161, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(67, 89, 49, 17);
		label_1.setText("密码:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(123, 86, 161, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		/**
		 *  btnNewButton 按钮对象
		 *  addSelectionListener 添加 xxx 事件监听器, 用于响应用户的操作
		 *  new SelectionAdapter() xxx 监听器类  匿名类
		 */
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				/**
				 * 1. 获取用户输入的用户名 和 密码
				 * 2. 调用用户的业务类的登录方法
				 * 3. 登录方法将会通知登录操作是否成功
				 * 4. 展示结果 
				 */
				String name = text.getText();// 获取用户的输入值
				String pwd = text_1.getText();// 获取用户的输入值
				// 创建消息框
				MessageBox mb = new MessageBox(shell);
				// 设置标题
				mb.setText("系统提示"); 
				
				UserBiz ubiz = new UserBiz();
				
				/*if("yc".equals(name) && "123".equals(pwd)) {
					mb.setMessage("登录成功!");
				} else {
					mb.setMessage("登录失败!");
				}*/
				// if 判断转换成 try catch
				try {
					ubiz.login(name, pwd);
					// 关闭当前窗口
					LoginWin.this.shell.close();
					// 打开主窗口
					new MainWin().open();
				} catch (BizException e1) {
					e1.printStackTrace();
					mb.setMessage(e1.getMessage());
				}
				// 打开消息框
				mb.open();  
				
			}
		});
		btnNewButton.setBounds(67, 150, 80, 27);
		btnNewButton.setText("登录");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 关闭窗口
				shell.close();
			}
		});
		button.setText("取消");
		button.setBounds(204, 150, 80, 27);

	}
}
