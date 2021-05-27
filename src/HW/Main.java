package HW;

public class Main {
    public static void main(String[] args) {
        if ("download".equals(command)) {
            // TODO: 16.05.2021 downloding

            downloading(out, in);


            private void downloading(DataOutputStream out, DataInputStream in) {
                try {
                    File file = new File("client/" + in.readUTF()); // read file name
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    long size = in.readLong();
                    byte[] buffer = new byte[8 * 1024];
                    for (int i = 0; i < (size + (8 * 1024 - 1)) / (8 * 1024); i++) {
                        int read = in.read(buffer);
                        fos.write(buffer, 0, read);
                    }
                    fos.close();
                    out.writeUTF("OK");
                } catch (Exception e) {
                    try {
                        out.writeUTF("WRONG");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            //---------//

            //---------//
            private void getFile(String filename) {
                // TODO: 16.05.2021 downloading

                try {
                    File file = new File("server/" + filename);
                    if (!file.exists()) {
                        throw new FileNotFoundException();
                    }
                    long fileLength = file.length();
                    FileInputStream fis = new FileInputStream(file);

                    out.writeUTF("download");
                    out.writeUTF(filename);
                    out.writeLong(fileLength);

                    int read = 0;
                    byte[] buffer = new byte[8 * 1024];
                    while ((read = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }

                    out.flush();

                    String status = in.readUTF();
                    System.out.println("Sending status: " + status);
                } catch (FileNotFoundException e) {
                    System.err.println("File not found - /server/" + filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
}
