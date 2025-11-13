package FirstTask;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {

        // Создание объекта класса testAnnotationClass и получение объекта Class, описывающего его структуру
        testAnnotationClass obj = new testAnnotationClass();
        Class<? extends testAnnotationClass> clazz = obj.getClass();

        // Перечисление всех объявленных методов класса
        for (Method method : clazz.getDeclaredMethods()) {
            // Проверка на наличие аннотации и модификатора protected или private
            if(method.isAnnotationPresent(Annotation.class) && !Modifier.isPublic(method.getModifiers())) {
                // Получение аннотации и её значения value
                Annotation annotation = method.getAnnotation(Annotation.class);
                int value = annotation.value();

                System.out.println("Annotated method " + method.getName() + " was found. Calling it " + value + " times:");

                // Объявление метода доступным
                method.setAccessible(true);

                // Вызов метода value раз с подстановкой аргументов
                for (int i = 0; i < value; i++) {
                    try{
                        if(method.getName().equals("ProtectedMethod1")) {
                            method.invoke(obj, "successful");
                        }
                        else if(method.getName().equals("ProtectedMethod3")) {
                            method.invoke(obj, "Admin", 1);
                        }
                        else if(method.getName().equals("PrivateMethod1")) {
                            method.invoke(obj, "successful");
                        }
                        else if(method.getName().equals("PrivateMethod2")) {
                            method.invoke(obj, 3);
                            System.out.println();
                        }
                    }
                    catch(Exception e){
                        System.out.println("Error in method " + method.getName() + " of class " + clazz.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
