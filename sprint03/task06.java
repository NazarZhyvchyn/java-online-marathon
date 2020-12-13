// Write your code here
import java.util.*;

enum SortOrder {
    ASC, DESC
}

class AddressBook implements Iterable<String> {

    private static class NameAddressPair {
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private final String firstName;
            private final String lastName;

            private Person(String firstName, String lastName) {
                Objects.requireNonNull(firstName);
                Objects.requireNonNull(lastName);
                this.firstName = firstName;
                this.lastName = lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(firstName, person.firstName) &&
                       Objects.equals(lastName, person.lastName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lastName);
            }
        }
    }

    private class AddressBookIterator implements Iterator<String> {
        private static final String ITERATOR_FORMAT =
            "First name: %s, Last name: %s, Address: %s";

        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < AddressBook.this.counter;
        }

        @Override
        public String next() {
            if (hasNext()) {
                NameAddressPair res = addressBook[this.counter++];
                return String.format(ITERATOR_FORMAT,
                    res.person.firstName, res.person.lastName, res.address);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity should be positive");
        }
        addressBook = new NameAddressPair[capacity];
    }

    private int find(NameAddressPair.Person person) {
        for (int i = 0; i < counter; i++) {
            if (Objects.equals(person, addressBook[i].person)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity() {
        final int increaseFactor = 2;
        if (counter + 1 > addressBook.length) {
            addressBook = Arrays.copyOf(addressBook, increaseFactor * addressBook.length);
        }
    }

    public boolean create(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        if (find(person) > -1) {
            return false;
        }
        ensureCapacity();
        addressBook[counter++] = new NameAddressPair(person, address);
        return true;
    }

    public String read(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        return ix > -1 ? addressBook[ix].address : null;
    }

    public boolean update(String firstName, String lastName, String address) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix > -1) {
            addressBook[ix].address = address;
            return true;
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        int ix = find(new NameAddressPair.Person(firstName, lastName));
        if (ix == -1) {
            return false;
        } else if (ix < counter - 1) {
            System.arraycopy(addressBook, ix + 1, addressBook, ix, counter - ix - 1);
        }
        addressBook[--counter] = null;
        return true;
    }

    public int size() {
        return counter;
    }

    public void sortedBy(SortOrder order) {
        final Comparator<String> comp =
                order == SortOrder.ASC ? Comparator.naturalOrder()
                : order == SortOrder.DESC ? Comparator.reverseOrder()
                : null;
        Arrays.sort(addressBook, 0, counter,
                new Comparator<NameAddressPair>() {
                    @Override
                    public int compare(NameAddressPair nap1, NameAddressPair nap2) {
                        int res = Objects.compare(nap1.person.firstName, nap2.person.firstName, comp);
                        if (res == 0) {
                            res = Objects.compare(nap1.person.lastName, nap2.person.lastName, comp);
                        }
                        return res;
                    }
                }
        );
    }

    @Override
    public Iterator<String> iterator() {
        return this.new AddressBookIterator();
    }
}