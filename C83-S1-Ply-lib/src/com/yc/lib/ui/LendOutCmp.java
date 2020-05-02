package com.yc.lib.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import com.yc.lib.bean.Book;
import com.yc.lib.bean.Lend;
import com.yc.lib.biz.BizException;
import com.yc.lib.biz.BookBiz;
import com.yc.lib.biz.LendBiz;
import com.yc.lib.util.DataHelper;
import com.yc.lib.util.SwtHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LendOutCmp extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LendOutCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label label = new Label(this, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 21);
		fd_label.left = new FormAttachment(0, 21);
		label.setLayoutData(fd_label);
		label.setText("图书编号:");
		
		text = new Text(this, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(label, 169, SWT.RIGHT);
		fd_text.top = new FormAttachment(0, 18);
		fd_text.left = new FormAttachment(label, 21);
		text.setLayoutData(fd_text);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 查询图书
				BookBiz bb = new BookBiz();
				try {
					Book book = bb.queryOne(DataHelper.asInteger(text.getText(),0));
					text_1.setText(book.getName());
					text_2.setText(DataHelper.asString(book.getStatus()));
				} catch (BizException e1) {
					e1.printStackTrace();
					SwtHelper.msg(getShell(), e1.getMessage());
				}
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(label, -5, SWT.TOP);
		fd_btnNewButton.right = new FormAttachment(text, 89, SWT.RIGHT);
		fd_btnNewButton.left = new FormAttachment(text, 27);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("查 询");
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if("0".equals(text_2.getText())== false) {
					SwtHelper.msg(getShell(), "该图书已经被借出!");
					return;
				}
				LendBiz lb = new LendBiz();
				Lend lend = new Lend();
				lend.setBookid(DataHelper.asInteger(text.getText(),0));
				lend.setClient(text_3.getText());
				/**
				 * 注意: 经办人临时使用 id = 1 的用户id
				 */
				lend.setEmpid(1);
				try {
					lb.lend(lend);
					SwtHelper.msg(getShell(), "借书成功!");
				} catch (BizException e1) {
					e1.printStackTrace();
					SwtHelper.msg(getShell(), e1.getMessage());
				}
				
				
				
			}
		});
		button.setText("借 出");
		FormData fd_button = new FormData();
		fd_button.right = new FormAttachment(btnNewButton, 80, SWT.RIGHT);
		fd_button.top = new FormAttachment(0, 16);
		fd_button.left = new FormAttachment(btnNewButton, 18);
		button.setLayoutData(fd_button);
		
		Label label_1 = new Label(this, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_1.top = new FormAttachment(label, 17);
		fd_label_1.left = new FormAttachment(0, 21);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("书名:");
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_1.top = new FormAttachment(text, 11);
		fd_text_1.left = new FormAttachment(label_1, 21);
		text_1.setLayoutData(fd_text_1);
		
		Label label_2 = new Label(this, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_2.top = new FormAttachment(label_1, 20);
		fd_label_2.left = new FormAttachment(label, 0, SWT.LEFT);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("借阅状态:");
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_2.top = new FormAttachment(text_1, 14);
		fd_text_2.left = new FormAttachment(label_2, 21);
		text_2.setLayoutData(fd_text_2);
		
		Label label_3 = new Label(this, SWT.NONE);
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(label_2, 19);
		fd_label_3.left = new FormAttachment(label, 0, SWT.LEFT);
		label_3.setLayoutData(fd_label_3);
		label_3.setText("借书人:");
		
		text_3 = new Text(this, SWT.BORDER);
		FormData fd_text_3 = new FormData();
		fd_text_3.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_3.top = new FormAttachment(text_2, 13);
		fd_text_3.left = new FormAttachment(label_3, 33);
		text_3.setLayoutData(fd_text_3);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
