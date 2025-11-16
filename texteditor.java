import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private StringBuilder currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    
    public void write(String text) {
        undoStack.push(currentText.toString()); 
        currentText.append(text);
        redoStack.clear(); 
        System.out.println("Teks ditambahkan: " + text);
    }

    
    public void show() {
        if (currentText.length() == 0) {
            System.out.println("Teks kosong.");
        } else {
            System.out.println("Isi teks:");
            System.out.println(currentText.toString());
        }
    }

    
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString()); 
            currentText = new StringBuilder(undoStack.pop()); 
            System.out.println("Undo berhasil.");
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }

   
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString()); 
            currentText = new StringBuilder(redoStack.pop()); 
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu Text Editor:");
            System.out.println("1. Write (Tambah teks)");
            System.out.println("2. Show (Tampilkan teks)");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Masukkan teks untuk ditambahkan: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case 2:
                    editor.show();
                    break;
                case 3:
                    editor.undo();
                    break;
                case 4:
                    editor.redo();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
