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
	
	
	private byte[] secureBidMessageDataPersonalSerializedHashed;

	private boolean isSecureBidMessageDataPersonalSerializedHashed;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
	private boolean isSecureBidMessageDataPersonalSerializedHashedVerified;
	
	private boolean isSecureBidMessageDataPersonalSerializedHashedValid;
	
	
	private byte[] secureBidMessageDataPersonalSerializedAndHashed;

	private boolean isSecureBidMessageDataPersonalSerializedAndHashed;
	

	private byte[] secureBidMessageDataPersonalSerializedAndHashedCiphered;

	private boolean isSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	

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
		
		this.secureBidMessageDataPersonalSerializedHashed = null;
		this.isSecureBidMessageDataPersonalSerializedHashed = false;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 0;
		this.isSecureBidMessageDataPersonalSerializedHashedVerified = false;
		this.isSecureBidMessageDataPersonalSerializedHashedValid = false;
		
		
		this.secureBidMessageDataPersonalSerializedAndHashed = null;
		this.isSecureBidMessageDataPersonalSerializedAndHashed = false;

		this.secureBidMessageDataPersonalSerializedAndHashedCiphered = null;
		this.isSecureBidMessageDataPersonalSerializedAndHashedCiphered = false;
		
	}


	public SecureBidMessageDataPersonal(byte[] secureBidMessageDataPersonalSerializedAndHashedCiphered,
										int sizeOfSecureBidMessageDataPersonalSerialized,
										int sizeOfSecureBidMessageDataPersonalSerializedHashed,
										int sizeOfUserEmailSerialized, int sizeOfUserHomeAddressSerialized,
										int sizeOfUserBankAccountNIBSerialized) {
		
		this.secureBidMessageDataPersonalSerializedAndHashedCiphered = secureBidMessageDataPersonalSerializedAndHashedCiphered;
		this.isSecureBidMessageDataPersonalSerializedAndHashedCiphered = true;
		
		this.secureBidMessageDataPersonalSerializedAndHashed = null;
		this.isSecureBidMessageDataPersonalSerializedAndHashed = true;
		
		this.secureBidMessageDataPersonalSerialized = null;
		this.isSecureBidMessageDataPersonalSerialized = true;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		
		this.secureBidMessageDataPersonalSerializedHashed = null;
		this.isSecureBidMessageDataPersonalSerializedHashed = true;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = sizeOfSecureBidMessageDataPersonalSerializedHashed;
		this.isSecureBidMessageDataPersonalSerializedHashedVerified = false;
		this.isSecureBidMessageDataPersonalSerializedHashedValid = false;
		
		
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

	
	public byte[] getSecureBidMessageDataPersonalSerializedHashed() {
		return this.secureBidMessageDataPersonalSerializedHashed;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedHashed() {
		return this.isSecureBidMessageDataPersonalSerializedHashed;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedHashed
		  (boolean isSecureBidMessageDataPersonalSerializedHashed) {
		
		this.isSecureBidMessageDataPersonalSerializedHashed = 
						isSecureBidMessageDataPersonalSerializedHashed;
	}
	
	public boolean getIsSecureBidMessageDataPersonalSerializedHashedVerified() {
		return this.isSecureBidMessageDataPersonalSerializedHashedVerified;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedHashedVerified
	      (boolean isSecureBidMessageDataPersonalSerializedHashedVerified) {
		
		this.isSecureBidMessageDataPersonalSerializedHashedVerified = 
					isSecureBidMessageDataPersonalSerializedHashedVerified;
		
	}
	
	public boolean getIsSecureBidMessageDataPersonalSerializedHashedValid() {
		return this.isSecureBidMessageDataPersonalSerializedHashedValid;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedHashedValid
    	  (boolean isSecureBidMessageDataPersonalSerializedHashedValid) {
	
		this.isSecureBidMessageDataPersonalSerializedHashedValid = 
				isSecureBidMessageDataPersonalSerializedHashedValid;
		
	}
	
	public byte[] getSecureBidMessageDataPersonalSerializedAndHashed() {
		return this.secureBidMessageDataPersonalSerializedAndHashed;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedAndHashed() {
		return this.isSecureBidMessageDataPersonalSerializedAndHashed;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedAndHashed
		  (boolean isSecureBidMessageDataPersonalSerializedAndHashed) {

		this.isSecureBidMessageDataPersonalSerializedAndHashed = 
				isSecureBidMessageDataPersonalSerializedAndHashed;

	}
	
	
	public byte[] getSecureBidMessageDataPersonalSerializedAndHashedCiphered() {
		return this.secureBidMessageDataPersonalSerializedAndHashedCiphered;
	}

	public boolean getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() {
		return this.isSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	}
	
	public void setIsSecureBidMessageDataPersonalSerializedAndHashedCiphered
		  (boolean isSecureBidMessageDataPersonalSerializedAndHashedCiphered) {

		this.isSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
				isSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		
	}
	
	
	public void buildSecureBidMessageDataPersonalToSend()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildSecureBidMessageDataPersonalToSend = 
				( !this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedHashed() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );	

		if(isPossibleToBuildSecureBidMessageDataPersonalToSend) {

			this.doSerializationOfSecureBidMessageDataPersonal();

			this.doHashOfSecureBidMessageDataPersonalSerialized();
						
			this.doSecureBidMessageDataPersonalSerializedAndHashed();

			this.encryptSecureBidMessageDataPersonalSerializedAndHashed();
			
		}

	}
	
	public void buildSecureBidMessageDataPersonalReceived() throws NoSuchAlgorithmException {
		
		boolean isPossibleToBuildSecureBidMessageDataPersonalReceived = 
				( !this.getIsSecureBidMessageDataPersonalSerialized() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedHashed() && 
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );
		
		if(isPossibleToBuildSecureBidMessageDataPersonalReceived) {
			
			this.decryptSecureBidMessageDataPersonalSerialized();
			
			this.undoSecureBidMessageDataPersonalSerializedAndHashed();
			
			if(this.checkIfHashOfSerializedSecureBidMessageDataPersonalIsValid()) {
				this.undoSecureBidMessageDataPersonalSerialization();
			}
			
		}
	}
	
	
	public void doSerializationOfSecureBidMessageDataPersonal() {

		boolean isPossibleToDoSecureBidMessageDataPersonalSerialization = 
				( !this.getIsSecureBidMessageDataPersonalSerialized() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

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
				  !this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

		if(isPossibleToUndoSecureBidMessageDataPersonalSerialization) {
			
			boolean isSecureBidMessageDataPersonalSerializedHashedVerifiedAndValid = 
					( this.getIsSecureBidMessageDataPersonalSerializedHashedVerified() && 
					  this.getIsSecureBidMessageDataPersonalSerializedHashedValid() );
				
				if(isSecureBidMessageDataPersonalSerializedHashedVerifiedAndValid) {
					
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

	}
	
	
	public void doHashOfSecureBidMessageDataPersonalSerialized() throws NoSuchAlgorithmException {

		boolean isPossibleToHashOfSecureBidMessageDataPersonalSerialized = 
						(  this.getIsSecureBidMessageDataPersonalSerialized() &&
						  !this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
						  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
						  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

		if(isPossibleToHashOfSecureBidMessageDataPersonalSerialized) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessageDataPersonalSerializedHashed = messageDigest.digest(this.secureBidMessageDataPersonalSerialized);


			this.setIsSecureBidMessageDataPersonalSerializedHashed(true);

		}

	}

	public boolean checkIfHashOfSerializedSecureBidMessageDataPersonalIsValid() throws NoSuchAlgorithmException {

		boolean isPossibleToCheckHashOfSecureBidMessageDataPersonalSerialized = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() &&
				   this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

		if(isPossibleToCheckHashOfSecureBidMessageDataPersonalSerialized) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bidSerializedHashedToCompare = messageDigest.digest(this.secureBidMessageDataPersonalSerialized);
			
			this.isSecureBidMessageDataPersonalSerializedHashedValid = 
									  Arrays.areEqual(this.secureBidMessageDataPersonalSerializedHashed, 
													  bidSerializedHashedToCompare) ? 
															  true : false;
			
			
			this.setIsSecureBidMessageDataPersonalSerializedHashedVerified(true);
			this.setIsSecureBidMessageDataPersonalSerializedHashed(false);
			
			
			return this.isSecureBidMessageDataPersonalSerializedHashedValid;
			
		}
		
		return false;
	}
	
	public void doSecureBidMessageDataPersonalSerializedAndHashed() {
	
		boolean isPossibleToDoSecureBidMessageDataPersonalSerializedAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() &&
				   this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

		if(isPossibleToDoSecureBidMessageDataPersonalSerializedAndHashed) {
			
			byte[] secureBidMessageDataPersonalSerialized = this.getSecureBidMessageDataPersonalSerialized();

			this.sizeOfSecureBidMessageDataPersonalSerialized = secureBidMessageDataPersonalSerialized.length;
			
			byte[] secureBidMessageDataPersonalSerializedHashed = this.getSecureBidMessageDataPersonalSerializedHashed();
			
			this.sizeOfSecureBidMessageDataPersonalSerializedHashed = secureBidMessageDataPersonalSerializedHashed.length;
			
			
			
			int sizeOfSecureBidMessageDataPersonalSerializedAndHashed = 
								( this.sizeOfSecureBidMessageDataPersonalSerialized + 
								  this.sizeOfSecureBidMessageDataPersonalSerializedHashed );

			
			this.secureBidMessageDataPersonalSerializedAndHashed = 
					new byte[ sizeOfSecureBidMessageDataPersonalSerializedAndHashed ];


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
			System.arraycopy(secureBidMessageDataPersonalSerialized, 0, this.secureBidMessageDataPersonalSerializedAndHashed, serializationOffset, secureBidMessageDataPersonalSerialized.length);
			serializationOffset += secureBidMessageDataPersonalSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataPersonalSerializedHashed, 0, this.secureBidMessageDataPersonalSerializedAndHashed, serializationOffset, secureBidMessageDataPersonalSerializedHashed.length);
			
			
			this.setIsSecureBidMessageDataPersonalSerializedAndHashed(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataPersonalSerializedAndHashed() {
		
		boolean isPossibleToUndoSecureBidMessageDataPersonalSerializedAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() &&
				   this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				   this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );

		if(isPossibleToUndoSecureBidMessageDataPersonalSerializedAndHashed) {
			
			this.secureBidMessageDataPersonalSerialized = new byte[this.sizeOfSecureBidMessageDataPersonalSerialized];

			this.secureBidMessageDataPersonalSerializedHashed = new byte[this.sizeOfSecureBidMessageDataPersonalSerializedHashed];
			
			
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
			System.arraycopy(this.secureBidMessageDataPersonalSerializedAndHashed, serializationOffset, this.secureBidMessageDataPersonalSerialized, 0, this.secureBidMessageDataPersonalSerialized.length);
			serializationOffset += secureBidMessageDataPersonalSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataPersonalSerializedAndHashed, serializationOffset, this.secureBidMessageDataPersonalSerializedHashed, 0, this.secureBidMessageDataPersonalSerializedHashed.length);
			
			
			
			
			this.setIsSecureBidMessageDataPersonalSerializedAndHashed(false);
			
		}
		
	}
	
	public void encryptSecureBidMessageDataPersonalSerializedAndHashed() {

		boolean isPossibleToEncryptSecureBidMessageDataPersonalSerializedAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() &&
				   this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				   this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				  !this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );


		if(isPossibleToEncryptSecureBidMessageDataPersonalSerializedAndHashed) {

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
				Cipher secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				byte[] initialisationVectorBytes = null;

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					
					secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageDataPersonal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureBidMessageDataPersonalSerializedAndHashedCiphered = 
						secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionCipher
						.doFinal(this.secureBidMessageDataPersonalSerialized);


				this.setIsSecureBidMessageDataPersonalSerializedAndHashedCiphered(true);		

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
		
		boolean isPossibleToDecryptSecureBidMessageDataPersonalSerializedAndHashed = 
				(  this.getIsSecureBidMessageDataPersonalSerialized() &&
				   this.getIsSecureBidMessageDataPersonalSerializedHashed() &&
				   this.getIsSecureBidMessageDataPersonalSerializedAndHashed() &&
				   this.getIsSecureBidMessageDataPersonalSerializedAndHashedCiphered() );


		if(isPossibleToDecryptSecureBidMessageDataPersonalSerializedAndHashed) {
			
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
				
				int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
						this.secureBidMessageDataPersonalSerializedAndHashedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessageDataPersonalSerializedAndHashed = 
			  			new byte[ secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
			  	                  .getOutputSize(sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered) ];
			    
			  	int sizeOfSecureBidMessageDataPersonalSerializedAndHashed = secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
										  									    .update(this.secureBidMessageDataPersonalSerializedAndHashedCiphered, 
										  										   	    0, sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
										  											    this.secureBidMessageDataPersonalSerializedAndHashed, 0);
			  	
			  	secureBidMessageDataPersonalSerializedAndHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessageDataPersonalSerializedAndHashed,
			  											    sizeOfSecureBidMessageDataPersonalSerializedAndHashed);
			    
			  	
				this.setIsSecureBidMessageDataPersonalSerializedAndHashedCiphered(false);		
				
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
}