import java.util.ArrayList;
import java.util.List;


public class Elevator {
    private int numOfFloors;  // 빌딩 층 수

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }


    List<Customer> registerList = new ArrayList<Customer>();  // 엘레베이터 안 고객 리스트

    private int curFloors = 1;

    private String directiion = "up";

    List<Customer> registerCustomer = new ArrayList<Customer>();
    List<Customer> cancelCustomer = new ArrayList<Customer>();


    public void move() {
        // 도착 층 수를 알아내는 과정
        // 최대층수까지 랜덤을 돌렸지만 고객들이 빌딩의 최대층수까지 꼭 간다는 보장이 없으니깐 찾는것
        int finalMaxFloor = registerList.get(0).getDstFloor();
        int finalMinFloor = registerList.get(0).getDstFloor();
        for (Customer cus : registerList) {
            if (finalMaxFloor < cus.getDstFloor()) {
                finalMaxFloor = cus.getDstFloor();
            }
            if (finalMinFloor > cus.getDstFloor()) {
                finalMinFloor = cus.getDstFloor();
            }
        }


        switch (directiion) {
            case "up" :
                while (curFloors <= finalMaxFloor) {
                    if (curFloors == numOfFloors) {
                        System.out.println("The Elevator is on the Top Floor Now");
                        directiion = "down";
                    }
                    if (curFloors != 1) {
                        System.out.printf("Elevator : %d -> %d\n", curFloors - 1, curFloors);
                    }

                    for (Customer cus : registerList) {
                        if (cus.getCurFloor() == curFloors) {
                            cus.setInElevator(true);
                            registerCustomer.add(cus);
                            System.out.printf("Customer %d -> Elevator\n", registerList.indexOf(cus) + 1);
                        }
                        if (cus.isInElevator() == true && cus.getDstFloor() == curFloors) {
                            cus.setInElevator(false);
                            cus.setFinished(true);
                            cancelCustomer.add(cus);
                            registerCustomer.remove(cus);
                            System.out.printf("Customer %d -> Destination Floor \n", registerList.indexOf(cus) + 1);
                        }
                    }
                    System.out.println("");
                    System.out.printf("Elevator's Current Floor : %d   Elevator's direction : %s\n",
                            curFloors, directiion);

                    System.out.println("Customer : ");
                    for (Customer cus : registerList) {
                        System.out.printf("    Customer %d    current floor : %d    destination floor : %d\n",
                                registerList.indexOf(cus) + 1, cus.getCurFloor(), cus.getDstFloor());
                        System.out.printf("                   in Elevator : %b    is Finished : %b\n",
                                cus.isInElevator(), cus.isFinished());
                    }
                    System.out.println("\n->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->\n");
                    curFloors++;
                }
            case "down" :
                curFloors = finalMaxFloor-1;
                while (curFloors >= finalMinFloor) {
                    System.out.printf("Elevator : %d -> %d\n", curFloors + 1, curFloors);

                    for (Customer cus : registerList) {
                        if (cus.isInElevator() == true && cus.getDstFloor() == curFloors) {
                            cus.setInElevator(false);
                            cus.setFinished(true);
                            cancelCustomer.add(cus);
                            registerCustomer.remove(cus);
                            System.out.printf("Customer %d -> Destination Floor \n", registerList.indexOf(cus) + 1);
                        }
                    }

                    System.out.printf("Elevator's Current Floor : %d   Elevator's direction : %s\n",
                            curFloors, directiion);
                    System.out.println("Customer : ");
                    for (Customer cus : registerList) {
                        System.out.printf("    Customer %d    current floor : %d    destination floor : %d\n",
                                registerList.indexOf(cus) + 1, cus.getCurFloor(), cus.getDstFloor());
                        System.out.printf("                   in Elevator : %b    is Finished : %b\n",
                                cus.isInElevator(), cus.isFinished());
                    }
                    System.out.println("\n->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->\n");

                    if (registerCustomer.isEmpty()) {
                        curFloors = -1;  // 엘레베이터를 멈추기위해 없는 층으로 설정
                        System.out.println("All Customers Arrived at Destination\n");
                    } else {
                        curFloors--;
                    }
                }
                break;
        }
    }
}
