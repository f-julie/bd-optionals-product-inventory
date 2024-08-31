import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ProductInventory collects groups of items to be shipped. It uses a 
 * ProductUtility to obtain individual product names, and to determine
 * whether each item is boxed and ready to be shipped.
 */
public class ProductInventory {
    private ProductUtility productUtility;
    private List<Integer> productIDs;

    /**
     * Constructor.
     * @param productUtility - The service used to retrieve product information
     * @param productIDs - A list of package IDs
     */
    public ProductInventory(ProductUtility productUtility, List<Integer> productIDs) {
        //
        // WARNING: DO NOT EDIT THE CONSTRUCTOR
        // Here's why: Typically, it's a good practice to validate constructor inputs.
        // However in this case, we're specifically asking for validation for these in
        // the methods will you be implementing, and the tests won't work correctly if you
        // do the validation here.
        //
        this.productUtility = productUtility;
        this.productIDs = productIDs;
    }

    /**
     * Find the product names for the IDs in the package.
     * @return Map[Integer, String] of product IDs to product names. Does not include products without names.
     */
    Map<Integer, String> findProductNames() {
        // TODO: replace stub implementation
        //Map<Integer, String> stubMap = new HashMap<Integer, String>();
        //for (Integer productID : productIDs) {
        //    stubMap.put(productID, "");
        //}
        //return stubMap;

        // Guard statements
        if (productUtility == null) {
            throw new IllegalArgumentException("productUtility is null");
        }
        if (productIDs == null) {
            throw new IllegalArgumentException("productID is null");
        }

        // Initialize the Map
        Map<Integer, String> productMap = new HashMap<>();

        // Loop over the list of Product IDs
        for (Integer productID : productIDs) {
            try {
                // Call the ProductUtility method to get the name
                String productName = productUtility.findProductName(productID);

                // If the productName is not null, add it to the map
                if (productName != null) {
                    productMap.put(productID, productName);
                }

            } catch (NullPointerException e) {
                // Handle the NullPointerException
                throw new IllegalArgumentException("productID is null");
            }
        }

        // Return the Map
        return productMap;
    }

    /**
     * Determine whether product is ready to ship or not.
     * @param productID the package identifier
     * @return Optional[Boolean] containing whether a product is ready to ship.
     */
    Optional<Boolean> isProductReady(Integer productID) {
        // TODO: replace stub implementation
        //Boolean stubResult = productUtility.isProductReady(0);
        //return Optional.of(stubResult);

        try {
            // Check for null productID first
            if (productID == null) {
                throw new IllegalArgumentException("The productID was null");
            }

            // Call the ProductUtility method
            Boolean isReady = productUtility.isProductReady(productID);

            // Return an empty Optional if the result is null
            if (isReady == null) {
                return Optional.empty();
            }

            // Wrap the Boolean in an Optional and return
            return Optional.of(isReady);

        } catch (NullPointerException e) {
            // Handle the NullPointerException
            throw new IllegalArgumentException("The productID was null");
        }
    }
}
