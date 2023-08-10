// Location Detection
// Implement a prototype service to detect a user's location based on their IP addresses.
// The IP addresses belonging to the IPv4 space are conventionally represented by four octets, 
// "a.b.c.d". such as 127.10.20.30. To keep it simple, these IP addresses are classified into 5 different regions 
// indexed from 1 to 5 on the basis of the order of the bits in the first octet.
// Broadly, the IP Addresses are categorized as follows:
// 1. 0.0.0.0-127.255.255.255
// 2. 128.0.0.0-191.255.255.255
// 3. 192.0.0.0-223.255.255.255
// 4. 224.0.0.0-239.255.255.255
// 5. 240.0.0.0 255.255.255.255
// Given a list of strings, ip_addresses, of size n, representing possible IPv4 addresses, for each 
// address, determine if it is a valid IP or not, and classify it into one of the 5 classes. Return an array of n 
// integers that represent the index of the regions for the corresponding IP addresses. Represent an invalid IP as -1.

// Example: Input :  ip_addresses = ["128.12.34.0", "31.258.90.11"]
// Output : [2,-1]


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IpPreFix{
    public int startRange;
    public int endRange;

    public IpPreFix(int startRange, int endRange){
        this.startRange = startRange;
        this.endRange = endRange;
    }
}


class LocationDetection{
    private static Map<IpPreFix, Integer> locationMap = new HashMap<IpPreFix, Integer>();

    private static void setLocationMap() {
        locationMap.put(new IpPreFix(0, 127), 1);
        locationMap.put(new IpPreFix(128, 191), 2);
        locationMap.put(new IpPreFix(192, 223), 3);
        locationMap.put(new IpPreFix(224, 239), 4);
        locationMap.put(new IpPreFix(240, 255), 5);
    }

    private static Boolean isIPvalid(String inpIp){
        String[] arrOfStr = inpIp.split("\\.");
        if (arrOfStr.length != 4){
            return false;
        }

        for (String ele : arrOfStr){
            int num = Integer.parseInt(ele);
            if (!(0 <= num && num <= 255)) {
                return false;
            }
        }

        return true;
    }

    private static List<Integer> getLocation(String[] inpArr) {

        List<Integer> result = new ArrayList<Integer>(); 
        for (String ip : inpArr){
            if (isIPvalid(ip)){
                String[] arrOfStr = ip.split("\\.");
                int firstNum = Integer.parseInt(arrOfStr[0]);

                for (Map.Entry<IpPreFix, Integer> entry : locationMap.entrySet()) {
                    IpPreFix key = entry.getKey();
                    Integer value = entry.getValue();
                    
                    if (key.startRange <= firstNum && key.endRange >= firstNum) {
                        result.add(value);
                        break;
                    }
                }
            }else{
                result.add(-1);
            }
        }
        return result;
    }

    public static void main(String[] args){
        LocationDetection.setLocationMap();
        String[] ipAddreesses = {"128.12.34.0", "31.258.90.11"};
        List<Integer> result = getLocation(ipAddreesses);
        System.out.println(result);
    }
}
