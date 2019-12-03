package main.java.messages.secure.bid.components.content.proposal.personal;

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

public class SecureBidMessagePersonalProposal {

	private String userEmail;

	private int sizeOfUserEmailSerialized;
	
	
	private String userHomeAddress;
	
	private int sizeOfUserHomeAddressSerialized;
	
	
	private String userBankAccountNIB;
	
	private int sizeOfUserBankAccountNIBSerialized;


	private byte[] secureBidMessagePersonalProposalSerialized;

	private boolean isSecureBidMessagePersonalProposalSerialized;

	private int sizeOfSecureBidMessagePersonalProposalSerialized;
	
	
	private byte[] secureBidMessagePersonalProposalSerializedHashed;

	private boolean isSecureBidMessagePersonalProposalSerializedHashed;
	
	private int sizeOfSecureBidMessagePersonalProposalSerializedHashed;
	
	private boolean isSecureBidMessagePersonalProposalSerializedHashedVerified;
	
	private boolean isSecureBidMessagePersonalProposalSerializedHashedValid;
	
	
	private byte[] secureBidMessagePersonalProposalSerializedAndHashed;

	private boolean isSecureBidMessagePersonalProposalSerializedAndHashed;

	private byte[] secureBidMessagePersonalProposalSerializedAndHashedCiphered;

	private boolean isSecureBidMessagePersonalProposalSerializedAndHashedCiphered;
	
	

	public SecureBidMessagePersonalProposal(String userEmail,
											String userHomeAddress,
											String userBankAccountNIB) {

		this.userEmail = userEmail;
		this.sizeOfUserEmailSerialized = 0;
		
		this.userHomeAddress = userHomeAddress;
		this.sizeOfUserHomeAddressSerialized = 0;
		
		this.userBankAccountNIB = userBankAccountNIB;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.secureBidMessagePersonalProposalSerialized = null;
		this.isSecureBidMessagePersonalProposalSerialized = false;
		this.sizeOfSecureBidMessagePersonalProposalSerialized = 0;
		
		this.secureBidMessagePersonalProposalSerializedHashed = null;
		this.isSecureBidMessagePersonalProposalSerializedHashed = false;
		this.sizeOfSecureBidMessagePersonalProposalSerializedHashed = 0;
		this.isSecureBidMessagePersonalProposalSerializedHashedVerified = false;
		this.isSecureBidMessagePersonalProposalSerializedHashedValid = false;
		
		
		this.secureBidMessagePersonalProposalSerializedAndHashed = null;
		this.isSecureBidMessagePersonalProposalSerializedAndHashed = false;

		this.secureBidMessagePersonalProposalSerializedAndHashedCiphered = null;
		this.isSecureBidMessagePersonalProposalSerializedAndHashedCiphered = false;

	}


	public SecureBidMessagePersonalProposal(byte[] secureBidMessagePersonalProposalSerializedAndHashedCiphered,
											int sizeOfSecureBidMessagePersonalProposalSerialized,
											int sizeOfSecureBidMessagePersonalProposalSerializedHashed,
											int sizeOfUserEmailSerialized, int sizeOfUserHomeAddress,
											int sizeOfUserBankAccountNIB) {
		
		this.secureBidMessagePersonalProposalSerializedAndHashedCiphered = secureBidMessagePersonalProposalSerializedAndHashedCiphered;
		this.isSecureBidMessagePersonalProposalSerializedAndHashedCiphered = true;
		
		this.secureBidMessagePersonalProposalSerializedAndHashed = null;
		this.isSecureBidMessagePersonalProposalSerializedAndHashed = true;
		
		this.secureBidMessagePersonalProposalSerializedHashed = null;
		this.isSecureBidMessagePersonalProposalSerializedHashed = true;
		this.sizeOfSecureBidMessagePersonalProposalSerializedHashed = sizeOfSecureBidMessagePersonalProposalSerializedHashed;
		this.isSecureBidMessagePersonalProposalSerializedHashedVerified = false;
		this.isSecureBidMessagePersonalProposalSerializedHashedValid = false;
		
		this.secureBidMessagePersonalProposalSerialized = null;
		this.isSecureBidMessagePersonalProposalSerialized = true;
		this.sizeOfSecureBidMessagePersonalProposalSerialized = sizeOfSecureBidMessagePersonalProposalSerialized;
		
		this.userEmail = null;
		
		
		this.userHomeAddress = null;
		
		
		this.userBankAccountNIB = null;
		
		
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

	
	public byte[] getSecureBidMessagePersonalProposalSerialized() {
		return this.secureBidMessagePersonalProposalSerialized;
	}

	public boolean getIsSecureBidMessagePersonalProposalSerialized() {
		return this.isSecureBidMessagePersonalProposalSerialized;
	}

	public void setIsSecureBidMessagePersonalProposalSerialized
		  (boolean isSecureBidMessagePersonalProposalSerialized) {

		this.isSecureBidMessagePersonalProposalSerialized = 
						isSecureBidMessagePersonalProposalSerialized;
		
	}

	
	public byte[] getSecureBidMessagePersonalProposalSerializedHashed() {
		return this.secureBidMessagePersonalProposalSerializedHashed;
	}

	public boolean getIsSecureBidMessagePersonalProposalSerializedHashed() {
		return this.isSecureBidMessagePersonalProposalSerializedHashed;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedHashed
		  (boolean isSecureBidMessagePersonalProposalSerializedHashed) {
		
		this.isSecureBidMessagePersonalProposalSerializedHashed = 
						isSecureBidMessagePersonalProposalSerializedHashed;
	}
	
	public boolean getIsSecureBidMessagePersonalProposalSerializedHashedVerified() {
		return this.isSecureBidMessagePersonalProposalSerializedHashedVerified;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedHashedVerified
	      (boolean isSecureBidMessagePersonalProposalSerializedHashedVerified) {
		
		this.isSecureBidMessagePersonalProposalSerializedHashedVerified = 
					isSecureBidMessagePersonalProposalSerializedHashedVerified;
		
	}
	
	public boolean getIsSecureBidMessagePersonalProposalSerializedHashedValid() {
		return this.isSecureBidMessagePersonalProposalSerializedHashedValid;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedHashedValid
    	  (boolean isSecureBidMessagePersonalProposalSerializedHashedValid) {
	
		this.isSecureBidMessagePersonalProposalSerializedHashedValid = 
				isSecureBidMessagePersonalProposalSerializedHashedValid;
		
	}
	
	public byte[] getSecureBidMessagePersonalProposalSerializedAndHashed() {
		return this.secureBidMessagePersonalProposalSerializedAndHashed;
	}

	public boolean getIsSecureBidMessagePersonalProposalSerializedAndHashed() {
		return this.isSecureBidMessagePersonalProposalSerializedAndHashed;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedAndHashed
		  (boolean isSecureBidMessagePersonalProposalSerializedAndHashed) {

		this.isSecureBidMessagePersonalProposalSerializedAndHashed = 
				isSecureBidMessagePersonalProposalSerializedAndHashed;

	}
	
	
	public byte[] getSecureBidMessagePersonalProposalSerializedAndHashedCiphered() {
		return this.secureBidMessagePersonalProposalSerializedAndHashedCiphered;
	}

	public boolean getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() {
		return this.isSecureBidMessagePersonalProposalSerializedAndHashedCiphered;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered
		  (boolean isSecureBidMessagePersonalProposalSerializedAndHashedCiphered) {

		this.isSecureBidMessagePersonalProposalSerializedAndHashedCiphered = 
				isSecureBidMessagePersonalProposalSerializedAndHashedCiphered;
		
	}
	
	
	public void doSecureBidMessagePersonalProposalSerialization() {

		boolean isPossibleToDoSecureBidMessagePersonalProposalSerialization = 
				( !this.getIsSecureBidMessagePersonalProposalSerialized() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToDoSecureBidMessagePersonalProposalSerialization) {

			byte[] userEmailSerialized = CommonUtils.fromStringToByteArray(this.userEmail);
			this.sizeOfUserEmailSerialized = userEmailSerialized.length;
			
			byte[] userHomeAddressSerialized = CommonUtils.fromStringToByteArray(this.userHomeAddress);
			this.sizeOfUserHomeAddressSerialized = userHomeAddressSerialized.length;
			
			byte[] userBankAccountNIBSerialized = CommonUtils.fromStringToByteArray(this.userBankAccountNIB);
			this.sizeOfUserBankAccountNIBSerialized = userBankAccountNIBSerialized.length;
			

			int sizeOfSecureBidMessagePersonalProposalSerialized = 
					( userEmailSerialized.length + 
							userHomeAddressSerialized.length + 
							userBankAccountNIBSerialized.length );

			this.secureBidMessagePersonalProposalSerialized = 
					new byte[ sizeOfSecureBidMessagePersonalProposalSerialized ];


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
			System.arraycopy(userEmailSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userHomeAddressSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userBankAccountNIBSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userBankAccountNIBSerialized.length);


			this.setIsSecureBidMessagePersonalProposalSerialized(true);

		}

	}

	public void undoSecureBidMessagePersonalProposalSerialization() {

		boolean isPossibleToUndoSecureBidMessagePersonalProposalSerialization = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToUndoSecureBidMessagePersonalProposalSerialization) {
			
			boolean isSecureBidMessagePersonalProposalSerializedHashedVerifiedAndValid = 
					( this.getIsSecureBidMessagePersonalProposalSerializedHashedVerified() && 
					  this.getIsSecureBidMessagePersonalProposalSerializedHashedValid() );
				
				if(isSecureBidMessagePersonalProposalSerializedHashedVerifiedAndValid) {
					
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
					System.arraycopy(this.secureBidMessagePersonalProposalSerialized, serializationOffset, userEmailSerialized, 0, userEmailSerialized.length);
					serializationOffset += userEmailSerialized.length;

					// Fills the byte array of the Block's Serialization with
					// the correspondent bytes from the current Bid serialized,
					// From the position corresponding to the length of the previous Bid's Serialization to
					// the position corresponding to the length of the current Bid's Serialization
					System.arraycopy(this.secureBidMessagePersonalProposalSerialized, serializationOffset, userHomeAddressSerialized, 0, userHomeAddressSerialized.length);
					serializationOffset += userHomeAddressSerialized.length;

					// Fills the byte array of the Block's Serialization with
					// the correspondent bytes from the current Bid serialized,
					// From the position corresponding to the length of the previous Bid's Serialization to
					// the position corresponding to the length of the current Bid's Serialization
					System.arraycopy(this.secureBidMessagePersonalProposalSerialized, serializationOffset, userBankAccountNIBSerialized, 0, userBankAccountNIBSerialized.length);
					
					
					this.userEmail = CommonUtils.fromByteArrayToString(userEmailSerialized);
					
					this.userHomeAddress = CommonUtils.fromByteArrayToString(userHomeAddressSerialized);
					
					this.userBankAccountNIB = CommonUtils.fromByteArrayToString(userBankAccountNIBSerialized);
					
					
					this.setIsSecureBidMessagePersonalProposalSerialized(false);
					
				}
			
		}

	}
	
	
	public void doHashOfSecureBidMessagePersonalProposalSerialized() throws NoSuchAlgorithmException {

		boolean isPossibleToHashOfSecureBidMessagePersonalProposalSerialized = 
						(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
						  !this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
						  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
						  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToHashOfSecureBidMessagePersonalProposalSerialized) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessagePersonalProposalSerializedHashed = messageDigest.digest(this.secureBidMessagePersonalProposalSerialized);


			this.setIsSecureBidMessagePersonalProposalSerializedHashed(true);

		}

	}

	public boolean checkIfHashOfSerializedBidIsValid() throws NoSuchAlgorithmException {

		boolean isPossibleToCheckHashOfSecureBidMessagePersonalProposalSerialized = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToCheckHashOfSecureBidMessagePersonalProposalSerialized) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bidSerializedHashedToCompare = messageDigest.digest(this.secureBidMessagePersonalProposalSerialized);
			
			this.isSecureBidMessagePersonalProposalSerializedHashedValid = 
									  Arrays.areEqual(this.secureBidMessagePersonalProposalSerializedHashed, 
													  bidSerializedHashedToCompare) ? 
															  true : false;
			
			
			this.setIsSecureBidMessagePersonalProposalSerializedHashedVerified(true);
			this.setIsSecureBidMessagePersonalProposalSerializedHashed(false);
			
			
			return this.isSecureBidMessagePersonalProposalSerializedHashedValid;
			
		}
		
		return false;
	}
	
	public void doSecureBidMessagePersonalProposalSerializedAndHashed() {
	
		boolean isPossibleToDoSecureBidMessagePersonalProposalSerializedAndHashed = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToDoSecureBidMessagePersonalProposalSerializedAndHashed) {
			
			byte[] secureBidMessagePersonalProposalSerialized = this.getSecureBidMessagePersonalProposalSerialized();

			this.sizeOfSecureBidMessagePersonalProposalSerialized = secureBidMessagePersonalProposalSerialized.length;
			
			byte[] secureBidMessagePersonalProposalSerializedHashed = this.getSecureBidMessagePersonalProposalSerializedHashed();
			
			this.sizeOfSecureBidMessagePersonalProposalSerializedHashed = secureBidMessagePersonalProposalSerializedHashed.length;
			
			
			
			int sizeOfSecureBidMessagePersonalProposalSerializedAndHashed = 
								( this.sizeOfSecureBidMessagePersonalProposalSerialized + 
								  this.sizeOfSecureBidMessagePersonalProposalSerializedHashed );

			
			this.secureBidMessagePersonalProposalSerializedAndHashed = 
					new byte[ sizeOfSecureBidMessagePersonalProposalSerializedAndHashed ];


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
			System.arraycopy(secureBidMessagePersonalProposalSerialized, 0, this.secureBidMessagePersonalProposalSerializedAndHashed, serializationOffset, secureBidMessagePersonalProposalSerialized.length);
			serializationOffset += secureBidMessagePersonalProposalSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessagePersonalProposalSerializedHashed, 0, this.secureBidMessagePersonalProposalSerializedAndHashed, serializationOffset, secureBidMessagePersonalProposalSerializedHashed.length);
			
			
			this.setIsSecureBidMessagePersonalProposalSerializedAndHashed(true);
			
		}
		
	}
	
	public void undoSecureBidMessagePersonalProposalSerializedAndHashed() {
		
		boolean isPossibleToUndoSecureBidMessagePersonalProposalSerializedAndHashed = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );

		if(isPossibleToUndoSecureBidMessagePersonalProposalSerializedAndHashed) {
			
			this.secureBidMessagePersonalProposalSerialized = new byte[this.sizeOfSecureBidMessagePersonalProposalSerialized];

			this.secureBidMessagePersonalProposalSerializedHashed = new byte[this.sizeOfSecureBidMessagePersonalProposalSerializedHashed];
			
			
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
			System.arraycopy(this.secureBidMessagePersonalProposalSerializedAndHashed, serializationOffset, this.secureBidMessagePersonalProposalSerialized, 0, this.secureBidMessagePersonalProposalSerialized.length);
			serializationOffset += secureBidMessagePersonalProposalSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessagePersonalProposalSerializedAndHashed, serializationOffset, this.secureBidMessagePersonalProposalSerializedHashed, 0, this.secureBidMessagePersonalProposalSerializedHashed.length);
			
			
			
			
			this.setIsSecureBidMessagePersonalProposalSerializedAndHashed(false);
			
		}
		
	}
	
	public void encryptSecureBidMessagePersonalProposalSerializedAndHashed() {

		boolean isPossibleToEncryptSecureBidMessagePersonalProposalSerializedAndHashed = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );


		if(isPossibleToEncryptSecureBidMessagePersonalProposalSerializedAndHashed) {

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
				Cipher secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				byte[] initialisationVectorBytes = null;

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					
					secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureBidMessagePersonalProposalSerializedAndHashedCiphered = 
						secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionCipher
						.doFinal(this.secureBidMessagePersonalProposalSerialized);


				this.setIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered(true);		

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

	public void decryptSecureBidMessagePersonalProposalSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessagePersonalProposalSerializedAndHashed = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedHashed() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedAndHashed() &&
				   this.getIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered() );


		if(isPossibleToDecryptSecureBidMessagePersonalProposalSerializedAndHashed) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				int sizeOfSecureBidMessagePersonalProposalSerializedAndHashedCiphered = 
						this.secureBidMessagePersonalProposalSerializedAndHashedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessagePersonalProposalSerializedAndHashed = 
			  			new byte[ secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher
			  	                  .getOutputSize(sizeOfSecureBidMessagePersonalProposalSerializedAndHashedCiphered) ];
			    
			  	int sizeOfSecureBidMessagePersonalProposalSerializedAndHashed = secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher
										  									    .update(this.secureBidMessagePersonalProposalSerializedAndHashedCiphered, 
										  										   	    0, sizeOfSecureBidMessagePersonalProposalSerializedAndHashedCiphered,
										  											    this.secureBidMessagePersonalProposalSerializedAndHashed, 0);
			  	
			  	secureBidMessagePersonalProposalSerializedAndHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessagePersonalProposalSerializedAndHashed,
			  											    sizeOfSecureBidMessagePersonalProposalSerializedAndHashed);
			    
			  	
				this.setIsSecureBidMessagePersonalProposalSerializedAndHashedCiphered(false);		
				
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
