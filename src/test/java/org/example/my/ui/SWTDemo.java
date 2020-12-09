package org.example.my.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.junit.Test;

public class SWTDemo {

    @Test
    public void helloWorld() {
        // Create Display
        Display display = new Display();
        // Create Shell (Window) from diplay
        Shell shell = new Shell(display);

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }


    @Test
    public void labelDemo() {
        Display display = Display.getDefault();
        Shell shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("SWT Application");

        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setBounds(98, 108, 61, 17);
        lblNewLabel.setText("New Label");

        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    /**
     * 文本框
     */
    @Test
    public void textDemo() {

        Display display = Display.getDefault();
        final Shell shell = new Shell();//如果这个地方不用final修饰,下面匿名内部类中取这个shell取不到.
        shell.setSize(450, 300);
        shell.setText("SWT Application");

        //------------------------新插入界面的核心代码---------------------
        final Text text = new Text(shell, SWT.BORDER);//定义一个文本框
        text.setBounds(85, 80, 182, 25);//定义文本框的定位

        //以上这两句可以自动生成,但是第一句的final关键字是要自己写上的.
        // text.setTextLimit(10);//最多只能输入10个字符
        text.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                System.out.println(text.getText());
            }
        });

        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * https://www.cnblogs.com/DreamDrive/p/4171868.html  (SWT中的布局之-----FormLayout(表格式布局))
     */
    @Test
    public void formLayoutDemo() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);

        // ---------创建窗口中的其他界面组件-------------
        FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = 100; // 左边距，单位：像素
        formLayout.marginHeight = 50; // 上边距
        shell.setLayout(formLayout);
        new Button(shell, SWT.NONE).setText("button1");

        // -----------------END------------------------
        shell.layout();

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    @Test
    public void formDataDemo() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);
        shell.setText("SWT Application");
        // ------------------新插入的界面核心代码------------------------
        shell.setLayout(new FormLayout());
        // new FormData ()
        Button button1 = new Button(shell, SWT.NONE);
        button1.setText("button1");
        FormData formData = new FormData();
        button1.setLayoutData(formData);
        // new FormData (int width, int height)，单位：像素
        Button button2 = new Button(shell, SWT.NONE);
        button2.setText("button2");
        FormData formData2 = new FormData(200, 50);// button2变成200长，50宽
        button2.setLayoutData(formData2);
        // ------------------END---------------------------------------------
        shell.layout();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    @Test
    public void formAttachmentDemo() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);
        shell.setText("SWT Application");
        // ------------------新插入的界面核心代码------------------------
        shell.setLayout(new FormLayout());
        new Text(shell, SWT.BORDER).setText("text1_formAttachmentDemo");

        //将button1应用FormData
        Button button1 = new Button(shell, SWT.NONE);
        button1.setText("button1");

        FormData formData = new FormData();
        formData.top = new FormAttachment(60, 0); // button1的顶端应用FormAttachment设置
        button1.setLayoutData(formData);
        // ------------------END---------------------------------------------
        shell.layout();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    @Test
    public void formAttachmentDemo2() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);
        shell.setText("SWT Application");
        // ------------------新插入的界面核心代码------------------------
        shell.setLayout(new FormLayout());
        new Text(shell, SWT.BORDER).setText("text1_formAttachmentDemo2");

        //将button1应用FormData
        Button button1 = new Button(shell, SWT.NONE);
        button1.setText("button1");

        FormData formData = new FormData();
        // 在new FormAttachment(60, 0)的基础上，再偏移30像素
        formData.top = new FormAttachment(60, 30); // button1的顶端应用FormAttachment设置
        button1.setLayoutData(formData);
        // ------------------END---------------------------------------------
        shell.layout();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    @Test
    public void formAttachmentDemo3() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);
        shell.setText("SWT Application");
        // ------------------新插入的界面核心代码------------------------
        shell.setLayout(new FormLayout());
        Text text1 = new Text(shell, SWT.BORDER);
        text1.setLayoutData(new FormData(100, 50));

        //定义并设置FormData
        FormData formData = new FormData();
        //以text1为基准偏移50像素
        FormAttachment formAttachment = new FormAttachment(text1,50);
        formData.top = formAttachment;
        formData.left = formAttachment;

        // 将button1应用FormData
        Button button1 = new Button(shell, SWT.NONE);
        button1.setText("button1");
        button1.setLayoutData(formData);
        // ------------------END---------------------------------------------
        shell.layout();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    @Test
    public void labelTextDemo() {
        final Display display = Display.getDefault();
        final Shell shell = new Shell();
        shell.setSize(327, 253);
        shell.setText("SWT Application");
        // ------------------新插入的界面核心代码------------------------

        // LabelText labelText11 = new LabelText(shell, "labelText11", null);

        // ------------------END---------------------------------------------
        shell.layout();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


    @Test
    public void labelDemo11() {

    }

    public static void main(String[] args) {
        SWTDemo entry2 = new SWTDemo();
    }
}
