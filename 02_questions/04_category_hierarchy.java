<<<<<<< HEAD
=======
// We're given categories that are organized hierarchically and a list of coupons where each coupon can be
// applied to certain categories. Coupons are valid for child categories of categories it can be applied to.
// First question: Given a category, return list of coupons applicable to that category.
// Second question: Each coupon now has a validity period, return list of coupons application to given
// category on a particular date.

>>>>>>> develop
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CategoryHierachy {

    Map<String, String> couponsMap = new HashMap<String, String>();
    Map<String, String> categoryMap = new HashMap<String, String>();
    
    static Map<String, String> createCouponMap(String value_1, String value_2){
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("CategoryName", value_1);
        myMap.put("CouponName", value_2);
        return myMap;
    }

    static Map<String, String> createCategoryMap(String value_1, String value_2){
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("CategoryName", value_1);
        myMap.put("CategoryParentName", value_2);
        return myMap;
    }

    public CategoryHierachy(List<Map<String, String>> coupons, List<Map<String, String>> category){

        for (Map<String, String> entry : coupons){
            couponsMap.put(entry.get("CategoryName"), entry.get("CouponName"));
        }

        for (Map<String, String> entry : category){
            categoryMap.put(entry.get("CategoryName"), entry.get("CategoryParentName"));
        }
    }

    public String find_coupons(String inputCategory){
        if (inputCategory == null){
            return null;
        }

        String ans = this.couponsMap.get(inputCategory);
        if (ans != null){
            return ans;
        }
        String parent = this.categoryMap.get(inputCategory);
        return this.find_coupons(parent);
    }


    public static void main(String[] args){
        // Map Coupons = new HashMap();

        List<Map<String, String>> coupons = new ArrayList<Map<String, String>>();
        coupons.add(createCouponMap("Comforter Sets", "Comforters Sale"));
        coupons.add(createCouponMap("Bedding", "Savings on Bedding"));
        coupons.add(createCouponMap("Bed & Bath", "Low price for Bed & Bath"));

        List<Map<String, String>> category = new ArrayList<Map<String, String>>();
        category.add(createCategoryMap("Comforter Sets", "Bedding"));
        category.add(createCategoryMap("Bedding", "Bed & Bath"));
        category.add(createCategoryMap("Bed & Bath", null));
        category.add(createCategoryMap("Soap Dispensers", "Bathroom Accessories"));
        category.add(createCategoryMap("Bathroom Accessories", "Bed & Bath"));
        category.add(createCategoryMap("Toy Organizers", "Baby And Kids"));
        category.add(createCategoryMap("Baby And Kids", null));

        CategoryHierachy categoryHierachy = new CategoryHierachy(coupons, category);
        System.out.println(categoryHierachy.find_coupons("Comforter Sets"));
        System.out.println(categoryHierachy.find_coupons("Bedding"));
        System.out.println(categoryHierachy.find_coupons("Bathroom Accessories"));
        System.out.println(categoryHierachy.find_coupons("Soap Dispensers"));
        System.out.println(categoryHierachy.find_coupons("Toy Organizers"));
    }
}
