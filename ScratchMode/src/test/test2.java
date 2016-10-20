package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test2 {

	public static void main(String[] args) throws IOException {
		
		String filePath1="C:\\Users\\Admin\\Desktop\\numbers\\12345_ster.wav";
		String filePath2="C:\\Users\\Admin\\Desktop\\numbers\\sip2.wav";
		File file1 = new File(filePath1);
		File file2 = new File(filePath2);
				
		//Открываем 1-й файл для записи
		BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(file1, true)); // true - добавление в конец файла
				
		//Открываем 2-й файл для считывания
		BufferedInputStream bufRead = new BufferedInputStream(new FileInputStream(file2));
		 int n;
		 while((n = bufRead.read()) != -1) {
			bufOut.write(n);
		 }
		 bufOut.flush();      // Принудительно выталкиваем данные с буфера
		 bufOut.close();     // Закрываем соединения
		 bufRead.close();
		
	}

}
