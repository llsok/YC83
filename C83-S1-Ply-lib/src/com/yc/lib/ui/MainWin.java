package com.yc.lib.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("基础数据");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("图书管理");
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.setText("员工管理");
		
		MenuItem menuItem_3 = new MenuItem(menu, SWT.CASCADE);
		menuItem_3.setText("图书借阅");
		
		Menu menu_2 = new Menu(menuItem_3);
		menuItem_3.setMenu(menu_2);
		
		MenuItem menuItem_4 = new MenuItem(menu_2, SWT.NONE);
		menuItem_4.setText("借出图书");
		
		MenuItem menuItem_5 = new MenuItem(menu_2, SWT.NONE);
		menuItem_5.setText("归还图书");
		
		MenuItem menuItem_6 = new MenuItem(menu, SWT.CASCADE);
		menuItem_6.setText("统计报表");
		
		Menu menu_3 = new Menu(menuItem_6);
		menuItem_6.setMenu(menu_3);
		
		MenuItem menuItem_7 = new MenuItem(menu_3, SWT.NONE);
		menuItem_7.setText("借出图书统计表");
		
		MenuItem menuItem_8 = new MenuItem(menu, SWT.NONE);
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		menuItem_8.setText("退出");

	}
}
