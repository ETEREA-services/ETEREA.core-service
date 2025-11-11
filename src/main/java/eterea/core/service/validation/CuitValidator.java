package eterea.core.service.validation;

/**
 * Validates CUIT number using Modulus 11 algorithm (extracted from VB6 code)
 * 
 */
public class CuitValidator {
       
    // Multiplier pattern for Modulus 11 algorithm
    private static final int[] MULTIPLIERS = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
    
    /**
     * Validates a CUIT number using Modulus 11 algorithm
     * 
     * @param cuit The CUIT to validate (can include hyphens or be 11 digits)
     * @return true if valid, false otherwise
     */
    public static boolean validate(String cuit) {
        if (cuit == null) {
            return false;
        }
        
        // Remove hyphens and trim
        cuit = cuit.trim().replace("-", "");
        
        // Must be exactly 11 digits
        if (cuit.length() != 11) {
            return false;
        }
        
        // Must be all numeric
        if (!cuit.matches("\\d{11}")) {
            return false;
        }
        
        // Cannot be all zeros or all ones
        if (cuit.equals("00000000000") || cuit.equals("11111111111")) {
            return false;
        }
        
        try {
            // Calculate weighted sum of first 10 digits
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                int digit = Character.getNumericValue(cuit.charAt(i));
                sum += digit * MULTIPLIERS[i];
            }
            
            // Calculate check digit
            int remainder = sum % 11;
            int checkDigit = 11 - remainder;
            
            // Handle special cases
            if (checkDigit == 11) {
                checkDigit = 0;
            } else if (checkDigit == 10) {
                checkDigit = 9;
            }
            
            // Compare with actual check digit (last digit)
            int actualCheckDigit = Character.getNumericValue(cuit.charAt(10));
            
            return checkDigit == actualCheckDigit;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
   
}
