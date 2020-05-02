package com.yc.lib.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.yc.lib.bean.Book;
import com.yc.lib.biz.BizException;
import com.yc.lib.biz.BookBiz;
import com.yc.lib.util.DataHelper;
import com.yc.lib.util.SwtHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookWin extends Dialog {

	// 关闭窗口时返回的数据
	protected boolean result = false;
	protected Shell shell;
	private Label lblNewLabel;
	private Text text_id;
	private Label lblNewLabel_1;
	private Text text_name;
	private Label lblNewLabel_2;
	private Text text_press;
	private Label lblIsbn;
	private Text text_isbn;
	private Composite composite;
	private Button button;
	private Button button_1;
	private Label lblNewLabel_3;
	private Text text_author;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Text text_pressdate;
	private Text text_createtime;
	private Text text_status;
	
	private Book book;
	/**
	 * 属性方法    对象方法   实例方法
	 * @param book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public BookWin(Shell parent, int style) {
		super(parent, style);
		setText("图书信息编辑");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(383, 365);
		shell.setText(getText());
		shell.setLocation((Display.getCurrent().getClientArea().width - shell.getSize().x) / 2,
				(Display.getCurrent().getClientArea().height - shell.getSize().y) / 2);
		GridLayout gl_shell = new GridLayout(2, false);
		gl_shell.verticalSpacing = 10;
		gl_shell.horizontalSpacing = 10;
		gl_shell.marginWidth = 10;
		gl_shell.marginHeight = 10;
		gl_shell.marginRight = 10;
		gl_shell.marginLeft = 10;
		gl_shell.marginBottom = 10;
		gl_shell.marginTop = 10;
		shell.setLayout(gl_shell);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 90;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("图书ID:");
		
		text_id = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_id.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("图书名称:");
		
		text_name = new Text(shell, SWT.BORDER);
		text_name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("出版社:");
		
		text_press = new Text(shell, SWT.BORDER);
		text_press.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblIsbn = new Label(shell, SWT.NONE);
		lblIsbn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblIsbn.setText("ISBN:");
		
		text_isbn = new Text(shell, SWT.BORDER);
		text_isbn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("作者:");
		
		text_author = new Text(shell, SWT.BORDER);
		text_author.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("出版时间:");
		
		text_pressdate = new Text(shell, SWT.BORDER);
		text_pressdate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("入库时间:");
		
		text_createtime = new Text(shell, SWT.BORDER);
		text_createtime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("状态:");
		
		text_status = new Text(shell, SWT.BORDER);
		text_status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setBounds(247, 10, 80, 27);
		button.setText("取 消");
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BookBiz bb = new BookBiz();
				Book book = new Book();
				// 将用户填写的信息加载到 book 中
				book.setId(DataHelper.asInteger(text_id.getText(), 0));
				book.setName(text_name.getText());
				book.setAuthor(text_author.getText());
				book.setPress(text_press.getText());
				book.setIsbn(text_isbn.getText());
				// "yyyy-[m]m-[d]d". 
				book.setPressdate(DataHelper.asDate(text_pressdate.getText(), null));
				
				try {
					bb.save(book);
					// result 表示保存成功
					result = true;
					shell.close();
				} catch (BizException e1) {
					e1.printStackTrace();
					SwtHelper.msg(shell, e1.getMessage());
				}
				
			}
		});
		button_1.setBounds(148, 10, 80, 27);
		button_1.setText("保 存");
		
		if(book != null ) {
			text_id.setText(DataHelper.asString(book.getId()));
			text_name.setText(DataHelper.asString(book.getName()));
			text_press.setText(DataHelper.asString(book.getPress()));
			text_isbn.setText(DataHelper.asString(book.getIsbn()));
			text_author.setText(DataHelper.asString(book.getAuthor()));
			text_pressdate.setText(DataHelper.asString(book.getPressdate()));
			text_createtime.setText(DataHelper.asString(book.getCreatetime()));
			text_status.setText(DataHelper.asString(book.getStatus()));
		}

	}
}
