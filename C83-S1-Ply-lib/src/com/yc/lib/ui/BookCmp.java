package com.yc.lib.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.yc.lib.bean.Book;
import com.yc.lib.biz.BizException;
import com.yc.lib.biz.BookBiz;
import com.yc.lib.util.DataHelper;
import com.yc.lib.util.SwtHelper;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookCmp extends Composite {
	private Text text_name;
	private Text text_author;
	private Text text_press;
	private Text text_isbn;
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BookCmp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(6, false));
		
		Label label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("书名");
		
		text_name = new Text(this, SWT.BORDER);
		text_name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("作者");
		
		text_author = new Text(this, SWT.BORDER);
		text_author.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				query();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 62;
		button.setLayoutData(gd_button);
		button.setText("查 询");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 打开图书编辑窗口
				boolean ret = new BookWin(getShell(),SWT.NONE).open();
				if(ret) {
					query();
				}
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 62;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("新 增");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("出版社");
		
		text_press = new Text(this, SWT.BORDER);
		text_press.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblIsbn = new Label(this, SWT.NONE);
		lblIsbn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIsbn.setText("ISBN");
		
		text_isbn = new Text(this, SWT.BORDER);
		text_isbn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(this, SWT.NONE);
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 62;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("修 改");
		
		Button button_3 = new Button(this, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelectionCount()==0) {
					SwtHelper.msg(getShell(), "请选择要删除的记录");
					return;
				}
				// 获取选中的所有行
				TableItem[] rows = table.getSelection();
				// 获取选中的第一行
				TableItem row = rows[0];
				
				// 提示图书是否删除
				if(SwtHelper.confirm(getShell(), "请确认是否要删除该图书")) {
					Integer id = DataHelper.asInteger(row.getText(0), 0);
					try {
						// 执行删除
						new BookBiz().delete(id);
						//刷新表格
						query();
					} catch (BizException e1) {
						e1.printStackTrace();
						SwtHelper.msg(getShell(), e1.getMessage());
					}
				}
			}
		});
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_3.widthHint = 62;
		button_3.setLayoutData(gd_button_3);
		button_3.setText("删 除");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("书名");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("作者");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("ISBN");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("出版社");
		query();
	}
	public void query() {
		Book b = new Book();
		b.setName(text_name.getText());
		b.setAuthor(text_author.getText());
		b.setPress(text_press.getText());
		b.setIsbn(text_isbn.getText());
		
		// 去掉表格中原有记录
		table.removeAll();
		// 创建图书业务对象
		BookBiz bb = new BookBiz();
		List<Book> list = bb.query(b);
		
		// 将集合中的图书信息, 写入表格
		for(Book book: list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] {
					book.getId().toString(),
					book.getName(),
					book.getAuthor(),
					book.getIsbn(),
					book.getPress()
			});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
