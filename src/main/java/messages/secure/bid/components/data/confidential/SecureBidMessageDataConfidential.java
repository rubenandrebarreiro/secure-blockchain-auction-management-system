package main.java.messages.secure.bid.components.data.confidential;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;
import main.java.common.utils.CommonUtils;

public class SecureBidMessageDataConfidential {

	private String userEmail;

	private int sizeOfUserEmailSerialized;
	
	
	private String userHomeAddress;
	
	private int sizeOfUserHomeAddressSerialized;
	
	
	private String userBankAccountNIB;
	
	private int sizeOfUserBankAccountNIBSerialized;


	private byte[] secureBidMessageDataConfidentialSerialized;

	private boolean isSecureBidMessageDataConfidentialSerialized;

	private int sizeOfSecureBidMessageDataConfidentialSerialized;
	
	
	private byte[] secretSymmetricKeyForDataConfidentialInBytes;
	
	
	private byte[] secureBidMessageDataConfidentialSerializedCiphered;

	private boolean isSecureBidMessageDataConfidentialSerializedCiphered;
	
	private int sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	
	
	private byte[] secureBidMessageDataConfidentialSerializedCipheredHashed;

	private boolean isSecureBidMessageDataConfidentialSerializedCipheredHashed;
	
	private int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	
	private boolean isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified;
	
	private boolean isSecureBidMessageDataConfidentialSerializedCipheredHashedValid;
	

	private byte[] secureBidMessageDataConfidentialSerializedCipheredAndHashed;

	private boolean isSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	
	

	public SecureBidMessageDataConfidential(String userEmail,
										String userHomeAddress,
										String userBankAccountNIB) {

		this.userEmail = userEmail;
		this.sizeOfUserEmailSerialized = 0;
		
		this.userHomeAddress = userHomeAddress;
		this.sizeOfUserHomeAddressSerialized = 0;
		
		this.userBankAccountNIB = userBankAccountNIB;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.secureBidMessageDataConfidentialSerialized = null;
		this.isSecureBidMessageDataConfidentialSerialized = false;
		this.sizeOfSecureBidMessageDataConfidentialSerialized = 0;
		
		this.secureBidMessageDataConfidentialSerializedCiphered = null;
		this.isSecureBidMessageDataConfidentialSerializedCiphered = false;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 0;
		
		this.secureBidMessageDataConfidentialSerializedCipheredHashed = null;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashed = false;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 0;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified = false;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid = false;
		
		this.secureBidMessageDataConfidentialSerializedCipheredAndHashed = null;
		this.isSecureBidMessageDataConfidentialSerializedCipheredAndHashed = false;
		
	}


	public SecureBidMessageDataConfidential(byte[] secureBidMessageDataConfidentialSerializedCipheredAndHashed,
										byte[] secretSymmetricKeyForDataConfidentialInBytes,
										int sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
										int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed,
										int sizeOfSecureBidMessageDataConfidentialSerialized,
										int sizeOfUserEmailSerialized,
										int sizeOfUserHomeAddressSerialized,
										int sizeOfUserBankAccountNIBSerialized) {
		
		this.secureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				secureBidMessageDataConfidentialSerializedCipheredAndHashed;
		this.isSecureBidMessageDataConfidentialSerializedCipheredAndHashed = true;
		
		this.secretSymmetricKeyForDataConfidentialInBytes = secretSymmetricKeyForDataConfidentialInBytes;
		
		this.secureBidMessageDataConfidentialSerializedCiphered = null;
		this.isSecureBidMessageDataConfidentialSerializedCiphered = true;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 
				sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
		
		this.secureBidMessageDataConfidentialSerializedCipheredHashed = null;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashed = true;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
				sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified = false;
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid = false;
		
		this.secureBidMessageDataConfidentialSerialized = null;
		this.isSecureBidMessageDataConfidentialSerialized = true;
		this.sizeOfSecureBidMessageDataConfidentialSerialized = 
				sizeOfSecureBidMessageDataConfidentialSerialized;
		
		this.userEmail = null;
		this.sizeOfUserEmailSerialized = sizeOfUserEmailSerialized;
		
		this.userHomeAddress = null;
		this.sizeOfUserHomeAddressSerialized = sizeOfUserHomeAddressSerialized;
		
		this.userBankAccountNIB = null;
		this.sizeOfUserBankAccountNIBSerialized = sizeOfUserBankAccountNIBSerialized;
		
	}


	public String getUserEmail() {
		return this.userEmail;
	}

	public String getUserHomeAddress() {
		return this.userHomeAddress;
	}

	public String getUserBankAccountNIB() {
		return this.userBankAccountNIB;
	}

	
	public byte[] getSecureBidMessageDataConfidentialSerialized() {
		return this.secureBidMessageDataConfidentialSerialized;
	}

	public boolean getIsSecureBidMessageDataConfidentialSerialized() {
		return this.isSecureBidMessageDataConfidentialSerialized;
	}

	public void setIsSecureBidMessageDataConfidentialSerialized
		  (boolean isSecureBidMessageDataConfidentialSerialized) {

		this.isSecureBidMessageDataConfidentialSerialized = 
				isSecureBidMessageDataConfidentialSerialized;
		
	}

	
	public byte[] getSecureBidMessageDataConfidentialSerializedCiphered() {
		return this.secureBidMessageDataConfidentialSerializedCiphered;
	}

	public boolean getIsSecureBidMessageDataConfidentialSerializedCiphered() {
		return this.isSecureBidMessageDataConfidentialSerializedCiphered;
	}
	
	public void setIsSecureBidMessageDataConfidentialSerializedCiphered
		  (boolean isSecureBidMessageDataConfidentialSerializedCiphered) {
		
		this.isSecureBidMessageDataConfidentialSerializedCiphered = 
						isSecureBidMessageDataConfidentialSerializedCiphered;
	
	}

	public int getSizeOfSecureBidMessageDataConfidentialSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	}
	
	
	public byte[] getSecureBidMessageDataConfidentialSerializedCipheredHashed() {
		return this.secureBidMessageDataConfidentialSerializedCipheredHashed;
	}

	public boolean getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() {
		return this.isSecureBidMessageDataConfidentialSerializedCipheredHashed;
	}
	
	public void setIsSecureBidMessageDataConfidentialSerializedCipheredHashed
		  (boolean isSecureBidMessageDataConfidentialSerializedCipheredHashed) {
		
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashed = 
					isSecureBidMessageDataConfidentialSerializedCipheredHashed;
	
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	}
	
	public byte[] getSecretSymmetricKeyForDataConfidentialInBytes() {
		return secretSymmetricKeyForDataConfidentialInBytes;
	}
	
	public boolean getIsSecureBidMessageDataConfidentialSerializedCipheredHashedVerified() {
		return this.isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified;
	}
	
	public void setIsSecureBidMessageDataConfidentialSerializedCipheredHashedVerified
		  (boolean isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified) {
	
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified =
				isSecureBidMessageDataConfidentialSerializedCipheredHashedVerified;

	}
	
	public boolean getIsSecureBidMessageDataConfidentialSerializedCipheredHashedValid() {
		return this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid;
	}
	
	public void setIsSecureBidMessageDataConfidentialSerializedCipheredHashedValid
		  (boolean isSecureBidMessageDataConfidentialSerializedCipheredHashedValid) {
	
		this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid =
				isSecureBidMessageDataConfidentialSerializedCipheredHashedValid;

	}
	
	public byte[] getSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
		return this.secureBidMessageDataConfidentialSerializedCipheredAndHashed;
	}

	public boolean getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
		return this.isSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	}
	
	public void setIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed
	      (boolean isSecureBidMessageDataConfidentialSerializedCipheredAndHashed) {
		
		this.isSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				isSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
		
	}
	
	
	
	public void buildSecureBidMessageDataConfidentialToSend()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildSecureBidMessageDataConfidentialToSend = 
				( !this.getIsSecureBidMessageDataConfidentialSerialized() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );	

		if(isPossibleToBuildSecureBidMessageDataConfidentialToSend) {

			this.doSerializationOfSecureBidMessageDataConfidential();

			this.encryptSecureBidMessageDataConfidentialSerialized();
			
			this.doHashOfSecureBidMessageDataConfidentialSerializedCiphered();
			
			this.doSecureBidMessageDataConfidentialSerializedCipheredAndHashed();
			
		}

	}
	
	public void buildSecureBidMessageDataConfidentialReceived() throws NoSuchAlgorithmException {
		
		boolean isPossibleToBuildSecureBidMessageDataConfidentialReceived = 
				( this.getIsSecureBidMessageDataConfidentialSerialized() && 
				  this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );	
		
		if(isPossibleToBuildSecureBidMessageDataConfidentialReceived) {
			
			this.undoSecureBidMessageDataConfidentialSerializedCipheredAndHashed();
			
			if(this.checkIfHashOfSecureBidMessageDataConfidentialSerializedCipheredIsValid()) {
				
				this.decryptSecureBidMessageDataConfidentialSerialized();
				
				this.undoSecureBidMessageDataConfidentialSerialization();
			}
			
		}
	}
	
	
	public void doSerializationOfSecureBidMessageDataConfidential() {

		boolean isPossibleToDoSecureBidMessageDataConfidentialSerialization = 
				( !this.getIsSecureBidMessageDataConfidentialSerialized() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );

		if(isPossibleToDoSecureBidMessageDataConfidentialSerialization) {

			byte[] userEmailSerialized = CommonUtils.fromStringToByteArray(this.userEmail);
			this.sizeOfUserEmailSerialized = userEmailSerialized.length;
			
			byte[] userHomeAddressSerialized = CommonUtils.fromStringToByteArray(this.userHomeAddress);
			this.sizeOfUserHomeAddressSerialized = userHomeAddressSerialized.length;
			
			byte[] userBankAccountNIBSerialized = CommonUtils.fromStringToByteArray(this.userBankAccountNIB);
			this.sizeOfUserBankAccountNIBSerialized = userBankAccountNIBSerialized.length;
			

			int sizeOfSecureBidMessageDataConfidentialSerialized = 
					( userEmailSerialized.length + 
							userHomeAddressSerialized.length + 
							userBankAccountNIBSerialized.length );

			this.secureBidMessageDataConfidentialSerialized = 
					new byte[ sizeOfSecureBidMessageDataConfidentialSerialized ];


			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array

			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userEmailSerialized, 0, this.secureBidMessageDataConfidentialSerialized, serializationOffset, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userHomeAddressSerialized, 0, this.secureBidMessageDataConfidentialSerialized, serializationOffset, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userBankAccountNIBSerialized, 0, this.secureBidMessageDataConfidentialSerialized, serializationOffset, userBankAccountNIBSerialized.length);


			this.setIsSecureBidMessageDataConfidentialSerialized(true);

		}

	}

	public void undoSecureBidMessageDataConfidentialSerialization() {

		boolean isPossibleToUndoSecureBidMessageDataConfidentialSerialization = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );

		if(isPossibleToUndoSecureBidMessageDataConfidentialSerialization) {
			
			byte[] userEmailSerialized = new byte[this.sizeOfUserEmailSerialized];
			
			byte[] userHomeAddressSerialized = new byte[this.sizeOfUserHomeAddressSerialized];
			
			byte[] userBankAccountNIBSerialized = new byte[this.sizeOfUserBankAccountNIBSerialized];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array

			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataConfidentialSerialized, serializationOffset, userEmailSerialized, 0, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataConfidentialSerialized, serializationOffset, userHomeAddressSerialized, 0, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataConfidentialSerialized, serializationOffset, userBankAccountNIBSerialized, 0, userBankAccountNIBSerialized.length);
			
			
			this.userEmail = CommonUtils.fromByteArrayToString(userEmailSerialized);
			
			this.userHomeAddress = CommonUtils.fromByteArrayToString(userHomeAddressSerialized);
			
			this.userBankAccountNIB = CommonUtils.fromByteArrayToString(userBankAccountNIBSerialized);
			
			
			this.setIsSecureBidMessageDataConfidentialSerialized(false);
						
		}

	}
	
	
	public void encryptSecureBidMessageDataConfidentialSerialized() {

		boolean isPossibleToEncryptSecureBidMessageDataConfidentialSerialized = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );


		if(isPossibleToEncryptSecureBidMessageDataConfidentialSerialized) {

			try {
				byte[] key = CommonUtils.createKeyForAES(256, new SecureRandom()).getEncoded();
				this.secretSymmetricKeyForDataConfidentialInBytes = key;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // TODO Symmetric Key generated on the fly with KeyGen (secretHMACKeyForDoSMitigationInBytes)

			try {

				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
				String symmetricEncryptionPadding = "PKCS7Padding";


				// Set the Secret Key and its specifications,
				// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(this.secretSymmetricKeyForDataConfidentialInBytes, symmetricEncryptionAlgorithm);


				String provider = "BC";
				Cipher secureBidMessageDataConfidentialSerializedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				byte[] initialisationVectorBytes = null;

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageDataConfidential.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageDataConfidentialSerializedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataConfidential.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					
					secureBidMessageDataConfidentialSerializedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataConfidential.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureBidMessageDataConfidentialSerializedSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureBidMessageDataConfidentialSerializedCiphered = 
						secureBidMessageDataConfidentialSerializedSymmetricEncryptionCipher
						.doFinal(this.secureBidMessageDataConfidentialSerialized);


				this.setIsSecureBidMessageDataConfidentialSerializedCiphered(true);		

			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Invalid Cryptographic Algorithm's Parameters!!!");
				invalidAlgorithmParameterException.printStackTrace();
			}
			catch (NoSuchProviderException noSuchProviderException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Cryptograhic Provider not found!!!");
				noSuchProviderException.printStackTrace();
			}
			catch (NoSuchPaddingException noSuchPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Padding Method not found!!!");
				noSuchPaddingException.printStackTrace();
			}
			catch (BadPaddingException badPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Bad/Wrong Padding Method in use!!!");
				badPaddingException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Invalid Cryptographic Algorithm's Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			catch (IllegalBlockSizeException illegalBlockSizeException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Illegal Cryptographic Algorithm's Block Size!!!");
				illegalBlockSizeException.printStackTrace();
			}	
		}

	}	

	public void decryptSecureBidMessageDataConfidentialSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessageDataConfidentialSerializedCiphered = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );


		if(isPossibleToDecryptSecureBidMessageDataConfidentialSerializedCiphered) {
			
			byte[] secretKeyBytes = this.secretSymmetricKeyForDataConfidentialInBytes; // TODO Symmetric Key contained in the Envelope (secretHMACKeyForDoSMitigationInBytes)
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageDataConfidential.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataConfidential.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataConfidential.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 
						this.secureBidMessageDataConfidentialSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessageDataConfidentialSerialized = 
			  			new byte[ secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered) ];
			    
			  	this.sizeOfSecureBidMessageDataConfidentialSerialized = secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher
										  								    .update(this.secureBidMessageDataConfidentialSerializedCiphered, 
										  									   	    0, this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
										  										    this.secureBidMessageDataConfidentialSerialized, 0);
			  	
			  	secureBidMessageDataConfidentialSerializedAndHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessageDataConfidentialSerialized,
			  											    this.sizeOfSecureBidMessageDataConfidentialSerialized);
			    
			  	
				this.setIsSecureBidMessageDataConfidentialSerializedCiphered(false);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Parameters!!!");
				invalidAlgorithmParameterException.printStackTrace();
			}
			catch (NoSuchProviderException noSuchProviderException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Cryptograhic Provider not found!!!");
				noSuchProviderException.printStackTrace();
			}
			catch (NoSuchPaddingException noSuchPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Padding Method not found!!!");
				noSuchPaddingException.printStackTrace();
			}
			catch (BadPaddingException badPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Bad/Wrong Padding Method in use!!!");
				badPaddingException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			catch (IllegalBlockSizeException illegalBlockSizeException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Illegal Cryptographic Algorithm's Block Size!!!");
				illegalBlockSizeException.printStackTrace();
			}
			catch (ShortBufferException shortBufferException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- The Buffer in use, during the Deciphering process it's not correct!!!");
				shortBufferException.printStackTrace();
			}
			
		}
	}	
	
	public void doHashOfSecureBidMessageDataConfidentialSerializedCiphered() throws NoSuchAlgorithmException {

		boolean isPossibleToHashOfSecureBidMessageDataConfidentialSerializedCiphered = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );

		if(isPossibleToHashOfSecureBidMessageDataConfidentialSerializedCiphered) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessageDataConfidentialSerializedCipheredHashed = 
					messageDigest.digest(secureBidMessageDataConfidentialSerializedCiphered);
			
			this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
					this.secureBidMessageDataConfidentialSerializedCipheredHashed.length;
			
			
			this.setIsSecureBidMessageDataConfidentialSerializedCipheredHashed(true);
			
		}

	}

	public boolean checkIfHashOfSecureBidMessageDataConfidentialSerializedCipheredIsValid()
		   throws NoSuchAlgorithmException {

		boolean isPossibleToCheckHashOfSecureBidMessageDataConfidentialSerializedCiphered = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );

		if(isPossibleToCheckHashOfSecureBidMessageDataConfidentialSerializedCiphered) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] secureBidMessageDataConfidentialSerializedHashedToCompare = 
					messageDigest.digest(this.secureBidMessageDataConfidentialSerializedCiphered);
			
			this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid = 
								Arrays.areEqual(this.secureBidMessageDataConfidentialSerializedCipheredHashed, 
										        secureBidMessageDataConfidentialSerializedHashedToCompare) ? 
												true : false;
			
			this.setIsSecureBidMessageDataConfidentialSerializedCipheredHashedVerified(true);
			this.setIsSecureBidMessageDataConfidentialSerializedCipheredHashed(false);
			
			
			return this.isSecureBidMessageDataConfidentialSerializedCipheredHashedValid;
			
		}
		
		return false;
	}
	
	public void doSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
	
		boolean isPossibleToDoSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );

		if(isPossibleToDoSecureBidMessageDataConfidentialSerializedCipheredAndHashed) {
			
			byte[] secureBidMessageDataConfidentialSerializedCiphered = this.getSecureBidMessageDataConfidentialSerializedCiphered();

			this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = secureBidMessageDataConfidentialSerializedCiphered.length;
			
			byte[] secureBidMessageDataConfidentialSerializedCipheredHashed = 
						this.getSecureBidMessageDataConfidentialSerializedCipheredHashed();
			
			this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
					secureBidMessageDataConfidentialSerializedCipheredHashed.length;
			
			
			
			int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
								( this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered + 
								  this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed );

			
			this.secureBidMessageDataConfidentialSerializedCipheredAndHashed = 
					new byte[ sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed ];


			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array

			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataConfidentialSerializedCiphered,
							 0, this.secureBidMessageDataConfidentialSerializedCipheredAndHashed,
							 serializationOffset, secureBidMessageDataConfidentialSerializedCiphered.length);
			serializationOffset += secureBidMessageDataConfidentialSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataConfidentialSerializedCipheredHashed,
					         0, this.secureBidMessageDataConfidentialSerializedCipheredAndHashed,
					         serializationOffset, secureBidMessageDataConfidentialSerializedCipheredHashed.length);
			
			
			this.setIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
		
		boolean isPossibleToUndoSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				(  this.getIsSecureBidMessageDataConfidentialSerialized() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCiphered() && 
				   this.getIsSecureBidMessageDataConfidentialSerializedCipheredHashed() &&
				   this.getIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed() );


		if(isPossibleToUndoSecureBidMessageDataConfidentialSerializedCipheredAndHashed) {
			
			this.secureBidMessageDataConfidentialSerializedCiphered = 
					new byte[this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered];

			this.secureBidMessageDataConfidentialSerializedCipheredHashed = 
					new byte[this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array

			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataConfidentialSerializedCipheredAndHashed,
							 serializationOffset, this.secureBidMessageDataConfidentialSerializedCiphered, 
							 0, this.secureBidMessageDataConfidentialSerializedCiphered.length);
			serializationOffset += secureBidMessageDataConfidentialSerializedCiphered.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataConfidentialSerializedCipheredAndHashed,
							 serializationOffset, this.secureBidMessageDataConfidentialSerializedCipheredHashed,
							 0, this.secureBidMessageDataConfidentialSerializedCipheredHashed.length);
			
			
			this.setIsSecureBidMessageDataConfidentialSerializedCipheredAndHashed(false);
			
		}
		
	}		
}