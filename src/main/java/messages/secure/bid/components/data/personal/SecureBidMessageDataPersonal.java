package main.java.messages.secure.bid.components.data.personal;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageDataPersonal {

	private String userEmail;

	private int sizeOfUserEmailSerialized;
	
	
	private String userHomeAddress;
	
	private int sizeOfUserHomeAddressSerialized;
	
	
	private String userBankAccountNIB;
	
	private int sizeOfUserBankAccountNIBSerialized;


	private byte[] secureBidMessageDataPersonalSerialized;

	private boolean isSecureBidMessageDataPersonalSerialized;

	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
	
	private byte[] secureBidMessageDataPersonalSerializedCiphered;

	private boolean isSecureBidMessageDataPersonalSerializedCiphered;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	
	
	
	
	private byte[] secureBidMessageDataPersonalSerializedCipheredHashed;

	private boolean isSecureBidMessageDataPersonalSerializedCipheredHashed;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	
	private boolean isSecureBidMessageDataPersonalSerializedCipheredHashedVerified;
	
	private boolean isSecureBidMessageDataPersonalSerializedCipheredHashedValid;
	

	private byte[] secureBidMessageDataPersonalSerializedCipheredAndHashed;

	private boolean isSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	
	

	public SecureBidMessageDataPersonal(String userEmail,
										String userHomeAddress,
										String userBankAccountNIB) {

		this.userEmail = userEmail;
		this.sizeOfUserEmailSerialized = 0;
		
		this.userHomeAddress = userHomeAddress;
		this.sizeOfUserHomeAddressSerialized = 0;
		
		this.userBankAccountNIB = userBankAccountNIB;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.secureBidMessageDataPersonalSerialized = null;
		this.isSecureBidMessageDataPersonalSerialized = false;
		this.sizeOfSecureBidMessageDataPersonalSerialized = 0;
		
		this.secureBidMessageDataPersonalSerializedCiphered = null;
		this.isSecureBidMessageDataPersonalSerializedCiphered = false;
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 0;
		
		this.secureBidMessageDataPersonalSerializedCipheredHashed = null;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashed = false;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 0;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedVerified = false;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid = false;
		
		this.secureBidMessageDataPersonalSerializedCipheredAndHashed = null;
		this.isSecureBidMessageDataPersonalSerializedCipheredAndHashed = false;
		
	}


	public SecureBidMessageDataPersonal(byte[] secureBidMessageDataPersonalSerializedCipheredAndHashed,
										int sizeOfSecureBidMessageDataPersonalSerializedCiphered,
										int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
										int sizeOfSecureBidMessageDataPersonalSerialized,
										int sizeOfUserEmailSerialized,
										int sizeOfUserHomeAddressSerialized,
										int sizeOfUserBankAccountNIBSerialized) {
		
		this.secureBidMessageDataPersonalSerializedCipheredAndHashed = 
				secureBidMessageDataPersonalSerializedCipheredAndHashed;
		this.isSecureBidMessageDataPersonalSerializedCipheredAndHashed = true;
		
		this.secureBidMessageDataPersonalSerializedCiphered = null;
		this.isSecureBidMessageDataPersonalSerializedCiphered = true;
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 
				sizeOfSecureBidMessageDataPersonalSerializedCiphered;
		
		this.secureBidMessageDataPersonalSerializedCipheredHashed = null;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashed = true;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedVerified = false;
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid = false;
		
		this.secureBidMessageDataPersonalSerialized = null;
		this.isSecureBidMessageDataPersonalSerialized = true;
		this.sizeOfSecureBidMessageDataPersonalSerialized = 
				sizeOfSecureBidMessageDataPersonalSerialized;
		
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

	
	public byte[] getSecureBidMessageDataPersonalSerialized() {
		return this.secureBidMessageDataPersonalSerialized;
	}

	public boolean getIsSecureBidMessageDataPersonalSerialized() {
		return this.isSecureBidMessageDataPersonalSerialized;
	}

	public void setIsSecureBidMessageDataPersonalSerialized
		  (boolean isSecureBidMessageDataPersonalSerialized) {

		this.isSecureBidMessageDataPersonalSerialized = 
				isSecureBidMessageDataPersonalSerialized;
		
	}

	
	public byte[] getSecureBidMessageDataPersonalSerializedCiphered() {
		return this.secureBidMessageDataPersonalSerializedCiphered;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedCiphered() {
		return this.isSecureBidMessageDataPersonalSerializedCiphered;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedCiphered
		  (boolean isSecureBidMessageDataPersonalSerializedCiphered) {
		
		this.isSecureBidMessageDataPersonalSerializedCiphered = 
						isSecureBidMessageDataPersonalSerializedCiphered;
	
	}

	public int getSizeOfSecureBidMessageDataPersonalSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	}
	
	
	public byte[] getSecureBidMessageDataPersonalSerializedCipheredHashed() {
		return this.secureBidMessageDataPersonalSerializedCipheredHashed;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedCipheredHashed() {
		return this.isSecureBidMessageDataPersonalSerializedCipheredHashed;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedCipheredHashed
		  (boolean isSecureBidMessageDataPersonalSerializedCipheredHashed) {
		
		this.isSecureBidMessageDataPersonalSerializedCipheredHashed = 
					isSecureBidMessageDataPersonalSerializedCipheredHashed;
	
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	}
	
	public boolean getIsSecureBidMessageDataPersonalSerializedCipheredHashedVerified() {
		return this.isSecureBidMessageDataPersonalSerializedCipheredHashedVerified;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedCipheredHashedVerified
		  (boolean isSecureBidMessageDataPersonalSerializedCipheredHashedVerified) {
	
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedVerified =
				isSecureBidMessageDataPersonalSerializedCipheredHashedVerified;

	}
	
	public boolean getIsSecureBidMessageDataPersonalSerializedCipheredHashedValid() {
		return this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedCipheredHashedValid
		  (boolean isSecureBidMessageDataPersonalSerializedCipheredHashedValid) {
	
		this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid =
				isSecureBidMessageDataPersonalSerializedCipheredHashedValid;

	}
	
	public byte[] getSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
		return this.secureBidMessageDataPersonalSerializedCipheredAndHashed;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
		return this.isSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedCipheredAndHashed
	      (boolean isSecureBidMessageDataPersonalSerializedCipheredAndHashed) {
		
		this.isSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				isSecureBidMessageDataPersonalSerializedCipheredAndHashed;
		
	}
	
	
	
	public void buildSecureBidMessageDataPersonalToSend()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildSecureBidMessageDataPersonalToSend = 
				( !this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );	

		if(isPossibleToBuildSecureBidMessageDataPersonalToSend) {

			this.doSerializationOfSecureBidMessageDataPersonal();

			this.encryptSecureBidMessageDataPersonalSerialized();
			
			this.doHashOfSecureBidMessageDataPersonalSerializedCiphered();
			
			this.doSecureBidMessageDataPersonalSerializedCipheredAndHashed();
			
		}

	}
	
	public void buildSecureBidMessageDataPersonalReceived() throws NoSuchAlgorithmException {
		
		boolean isPossibleToBuildSecureBidMessageDataPersonalReceived = 
				( this.getIsSecureBidMessageDataPersonalSerialized() && 
				  this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );	
		
		if(isPossibleToBuildSecureBidMessageDataPersonalReceived) {
			
			this.undoSecureBidMessageDataPersonalSerializedCipheredAndHashed();
			
			if(this.checkIfHashOfSecureBidMessageDataPersonalSerializedCipheredIsValid()) {
				
				this.decryptSecureBidMessageDataPersonalSerialized();
				
				this.undoSecureBidMessageDataPersonalSerialization();
			}
			
		}
	}
	
	
	public void doSerializationOfSecureBidMessageDataPersonal() {

		boolean isPossibleToDoSecureBidMessageDataPersonalSerialization = 
				( !this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );

		if(isPossibleToDoSecureBidMessageDataPersonalSerialization) {

			byte[] userEmailSerialized = CommonUtils.fromStringToByteArray(this.userEmail);
			this.sizeOfUserEmailSerialized = userEmailSerialized.length;
			
			byte[] userHomeAddressSerialized = CommonUtils.fromStringToByteArray(this.userHomeAddress);
			this.sizeOfUserHomeAddressSerialized = userHomeAddressSerialized.length;
			
			byte[] userBankAccountNIBSerialized = CommonUtils.fromStringToByteArray(this.userBankAccountNIB);
			this.sizeOfUserBankAccountNIBSerialized = userBankAccountNIBSerialized.length;
			

			int sizeOfSecureBidMessageDataPersonalSerialized = 
					( userEmailSerialized.length + 
							userHomeAddressSerialized.length + 
							userBankAccountNIBSerialized.length );

			this.secureBidMessageDataPersonalSerialized = 
					new byte[ sizeOfSecureBidMessageDataPersonalSerialized ];


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
			System.arraycopy(userEmailSerialized, 0, this.secureBidMessageDataPersonalSerialized, serializationOffset, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userHomeAddressSerialized, 0, this.secureBidMessageDataPersonalSerialized, serializationOffset, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userBankAccountNIBSerialized, 0, this.secureBidMessageDataPersonalSerialized, serializationOffset, userBankAccountNIBSerialized.length);


			this.setIsSecureBidMessageDataPersonalSerialized(true);

		}

	}

	public void undoSecureBidMessageDataPersonalSerialization() {

		boolean isPossibleToUndoSecureBidMessageDataPersonalSerialization = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );

		if(isPossibleToUndoSecureBidMessageDataPersonalSerialization) {
			
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
			System.arraycopy(this.secureBidMessageDataPersonalSerialized, serializationOffset, userEmailSerialized, 0, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataPersonalSerialized, serializationOffset, userHomeAddressSerialized, 0, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataPersonalSerialized, serializationOffset, userBankAccountNIBSerialized, 0, userBankAccountNIBSerialized.length);
			
			
			this.userEmail = CommonUtils.fromByteArrayToString(userEmailSerialized);
			
			this.userHomeAddress = CommonUtils.fromByteArrayToString(userHomeAddressSerialized);
			
			this.userBankAccountNIB = CommonUtils.fromByteArrayToString(userBankAccountNIBSerialized);
			
			
			this.setIsSecureBidMessageDataPersonalSerialized(false);
						
		}

	}
	
	
	public void encryptSecureBidMessageDataPersonalSerialized() {

		boolean isPossibleToEncryptSecureBidMessageDataPersonalSerialized = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );


		if(isPossibleToEncryptSecureBidMessageDataPersonalSerialized) {

			// TODO - to complete

			byte[] secretKeyBytes = null;

			try {

				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
				String symmetricEncryptionPadding = "NoPadding";


				// Set the Secret Key and its specifications,
				// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);


				String provider = "BC";
				Cipher secureBidMessageDataPersonalSerializedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				byte[] initialisationVectorBytes = null;

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageDataPersonalSerializedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					
					secureBidMessageDataPersonalSerializedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureBidMessageDataPersonalSerializedSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureBidMessageDataPersonalSerializedCiphered = 
						secureBidMessageDataPersonalSerializedSymmetricEncryptionCipher
						.doFinal(this.secureBidMessageDataPersonalSerialized);


				this.setIsSecureBidMessageDataPersonalSerializedCiphered(true);		

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

	public void decryptSecureBidMessageDataPersonalSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessageDataPersonalSerializedCiphered = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );


		if(isPossibleToDecryptSecureBidMessageDataPersonalSerializedCiphered) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageDataPersonal.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 
						this.secureBidMessageDataPersonalSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessageDataPersonalSerialized = 
			  			new byte[ secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
			  	                  .getOutputSize(sizeOfSecureBidMessageDataPersonalSerializedCiphered) ];
			    
			  	this.sizeOfSecureBidMessageDataPersonalSerialized = secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
										  								    .update(this.secureBidMessageDataPersonalSerializedCiphered, 
										  									   	    0, sizeOfSecureBidMessageDataPersonalSerializedCiphered,
										  										    this.secureBidMessageDataPersonalSerialized, 0);
			  	
			  	secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessageDataPersonalSerialized,
			  											    this.sizeOfSecureBidMessageDataPersonalSerialized);
			    
			  	
				this.setIsSecureBidMessageDataPersonalSerializedCiphered(false);		
				
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
	
	public void doHashOfSecureBidMessageDataPersonalSerializedCiphered() throws NoSuchAlgorithmException {

		boolean isPossibleToHashOfSecureBidMessageDataPersonalSerializedCiphered = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );

		if(isPossibleToHashOfSecureBidMessageDataPersonalSerializedCiphered) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessageDataPersonalSerializedCipheredHashed = 
					messageDigest.digest(secureBidMessageDataPersonalSerializedCiphered);
			
			this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
					this.secureBidMessageDataPersonalSerializedCipheredHashed.length;
			
			
			this.setIsSecureBidMessageDataPersonalSerializedCipheredHashed(true);
			
		}

	}

	public boolean checkIfHashOfSecureBidMessageDataPersonalSerializedCipheredIsValid()
		   throws NoSuchAlgorithmException {

		boolean isPossibleToCheckHashOfSecureBidMessageDataPersonalSerializedCiphered = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );

		if(isPossibleToCheckHashOfSecureBidMessageDataPersonalSerializedCiphered) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] secureBidMessageDataPersonalSerializedHashedToCompare = 
					messageDigest.digest(this.secureBidMessageDataPersonalSerializedCiphered);
			
			this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid = 
								Arrays.areEqual(this.secureBidMessageDataPersonalSerializedCipheredHashed, 
										        secureBidMessageDataPersonalSerializedHashedToCompare) ? 
												true : false;
			
			this.setIsSecureBidMessageDataPersonalSerializedCipheredHashedVerified(true);
			this.setIsSecureBidMessageDataPersonalSerializedCipheredHashed(false);
			
			
			return this.isSecureBidMessageDataPersonalSerializedCipheredHashedValid;
			
		}
		
		return false;
	}
	
	public void doSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
	
		boolean isPossibleToDoSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );

		if(isPossibleToDoSecureBidMessageDataPersonalSerializedCipheredAndHashed) {
			
			byte[] secureBidMessageDataPersonalSerializedCiphered = this.getSecureBidMessageDataPersonalSerializedCiphered();

			this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = secureBidMessageDataPersonalSerializedCiphered.length;
			
			byte[] secureBidMessageDataPersonalSerializedCipheredHashed = 
						this.getSecureBidMessageDataPersonalSerializedCipheredHashed();
			
			this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
					secureBidMessageDataPersonalSerializedCipheredHashed.length;
			
			
			
			int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
								( this.sizeOfSecureBidMessageDataPersonalSerializedCiphered + 
								  this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed );

			
			this.secureBidMessageDataPersonalSerializedCipheredAndHashed = 
					new byte[ sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed ];


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
			System.arraycopy(secureBidMessageDataPersonalSerializedCiphered,
							 0, this.secureBidMessageDataPersonalSerializedCipheredAndHashed,
							 serializationOffset, secureBidMessageDataPersonalSerializedCiphered.length);
			serializationOffset += secureBidMessageDataPersonalSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataPersonalSerializedCipheredHashed,
					         0, this.secureBidMessageDataPersonalSerializedCipheredAndHashed,
					         serializationOffset, secureBidMessageDataPersonalSerializedCipheredHashed.length);
			
			
			this.setIsSecureBidMessageDataPersonalSerializedCipheredAndHashed(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
		
		boolean isPossibleToUndoSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCiphered() && 
				   this.getIsSecureBidMessageDataPersonalSerializedCipheredHashed() &&
				   this.getIsSecureBidMessageDataPersonalSerializedCipheredAndHashed() );


		if(isPossibleToUndoSecureBidMessageDataPersonalSerializedCipheredAndHashed) {
			
			this.secureBidMessageDataPersonalSerializedCiphered = 
					new byte[this.sizeOfSecureBidMessageDataPersonalSerializedCiphered];

			this.secureBidMessageDataPersonalSerializedCipheredHashed = 
					new byte[this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed];
			
			
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
			System.arraycopy(this.secureBidMessageDataPersonalSerializedCipheredAndHashed,
							 serializationOffset, this.secureBidMessageDataPersonalSerializedCiphered, 
							 0, this.secureBidMessageDataPersonalSerializedCiphered.length);
			serializationOffset += secureBidMessageDataPersonalSerializedCiphered.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataPersonalSerializedCipheredAndHashed,
							 serializationOffset, this.secureBidMessageDataPersonalSerializedCipheredHashed,
							 0, this.secureBidMessageDataPersonalSerializedCipheredHashed.length);
			
			
			this.setIsSecureBidMessageDataPersonalSerializedCipheredAndHashed(false);
			
		}
		
	}	
}