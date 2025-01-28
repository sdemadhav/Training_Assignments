class CovariantDemo {
    static class Animal {
        public void sound() {
            System.out.println("Animal sound");
        }
    }

    static class Dog extends Animal {
        public void sound() {
            System.out.println("Bhau Bhau !");
        }
    }

    static class Contravariant<Animal> {
        private Animal animal;

        public Contravariant(Animal animal) {
            this.animal = animal;
        }

        public void setAnimal(Animal animal) {
            this.animal = animal;
        }

        public Animal getAnimal() {
            return animal;
        }
    }

    static class Invariant<T> {
        private T obj;

        public Invariant(T obj) {
            this.obj = obj;
        }

        public T getObj() {
            return obj;
        }
    }

    public static void main(String[] args) {
       
        Animal myAnimal = new Dog(); 
        myAnimal.sound();

        
        Contravariant<Animal> contravariant = new Contravariant<>(new Animal());
        contravariant.setAnimal(new Dog()); 

      
        Invariant<Dog> invariant = new Invariant<>(new Dog());
        Dog myDog = invariant.getObj();
        myDog.sound(); 
    }
}

