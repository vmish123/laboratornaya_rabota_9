package FirstTask;
public class testAnnotationClass {

    // Публичные методы

    public void PublicMethod1(int a, int b) {
        System.out.println("Public PublicMethod1 was called with output: " + a*b);
    }

    @Annotation(value = 2)
    public String PublicMethod2(int a) {
        return "Public PublicMethod2 was called with output: " + a;
    }

    // Защищённые методы

    @Annotation(value = 4)
    protected void ProtectedMethod1(String str) {
        System.out.println("Protected ProtectedMethod1 was called with following message: " + str);
    }

    protected String ProtectedMethod2(boolean flag) {
        System.out.println("Protected ProtectedMethod2 was called");
        return "Current status: " + flag;
    }
    @Annotation(value = 6)
    protected void ProtectedMethod3(String usr, int id) {
        System.out.println(usr + " with ID " + id + " called protected ProtectedMethod3");
    }

    // Приватные методы

    @Annotation(value = 2)
    private void PrivateMethod1(String str) {
        System.out.println("Private PrivateMethod1 was called with following message: " + str);
    }

    @Annotation(value = 3)
    private void PrivateMethod2(int a) {
        System.out.print("Private PrivateMethod2 was called with value: " + a + ". Returning doubled value: " + a*2);
    }
}
