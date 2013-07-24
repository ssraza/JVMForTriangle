/*
 * This Class will give the bytecode String value
 * for their int value.
 */
package com.gannon.jvm.utilities;

public class OpcodeUtility {
	public static int nextVariableID = 1000;
	public final static int MAX_PARAMETER_ID_ALLOWED = nextVariableID-1;
	public static final int END_INSTRUCTION_FLAG = -1;

	public static String getOpCodeCommand(int Opcode) {
		String opCode = null;
		switch (Opcode) {
		case 00:
			opCode = "nop";
			break;
		case 01:
			opCode = "aconst_null";
			break;
		case 02:
			opCode = "iconst_m1";
			break;
		case 03:
			opCode = "iconst_0";
			break;
		case 04:
			opCode = "iconst_1";
			break;
		case 05:
			opCode = "iconst_2";
			break;
		case 06:
			opCode = "iconst_3";
			break;
		case 07:
			opCode = "iconst_4";
			break;
		case 8:
			opCode = "iconst_5";
			break;
		case 9:
			opCode = "lconst_0";
			break;
		case 10:
			opCode = "lconst_1";
			break;
		case 11:
			opCode = "fconst_0";
			break;
		case 12:
			opCode = "fconst_1";
			break;
		case 13:
			opCode = "fconst_2";
			break;
		case 14:
			opCode = "dconst_0";
			break;
		case 15:
			opCode = "dconst_1";
			break;
		case 16:
			opCode = "bipush";
			break;
		case 17:
			opCode = "sipush";
			break;
		case 18:
			opCode = "ldc";
			break;
		case 19:
			opCode = "ldc_w";
			break;
		case 20:
			opCode = "ldc2_w";
			break;
		case 21:
			opCode = "iload";
			break;
		case 22:
			opCode = "lload";
			break;
		case 23:
			opCode = "fload";
			break;
		case 24:
			opCode = "dload";
			break;
		case 25:
			opCode = "aload";
			break;
		case 26:
			opCode = "iload_0";
			break;
		case 27:
			opCode = "iload_1";
			break;
		case 28:
			opCode = "iload_2";
			break;
		case 29:
			opCode = "iload_3";
			break;
		case 30:
			opCode = "lload_0";
			break;
		case 31:
			opCode = "lload_1";
			break;
		case 32:
			opCode = "lload_2";
			break;
		case 33:
			opCode = "lload_3";
			break;
		case 34:
			opCode = "fload_0";
			break;
		case 35:
			opCode = "fload_1";
			break;
		case 36:
			opCode = "fload_2";
			break;
		case 37:
			opCode = "fload_3";
			break;
		case 38:
			opCode = "dload_0";
			break;
		case 39:
			opCode = "dload_1";
			break;
		case 40:
			opCode = "dload_2";
			break;
		case 41:
			opCode = "dload_3";
			break;
		case 42:
			opCode = "aload_0";
			break;
		case 43:
			opCode = "aload_1";
			break;
		case 44:
			opCode = "aload_2";
			break;
		case 45:
			opCode = "aload_3";
			break;
		case 46:
			opCode = "iaload";
			break;
		case 47:
			opCode = "laload";
			break;
		case 48:
			opCode = "faload";
			break;
		case 49:
			opCode = "daload";
			break;
		case 50:
			opCode = "aaload";
			break;
		case 51:
			opCode = "baload";
			break;
		case 52:
			opCode = "caload";
			break;
		case 53:
			opCode = "saload";
			break;
		case 54:
			opCode = "istore";
			break;
		case 55:
			opCode = "lstore";
			break;
		case 56:
			opCode = "fstore";
			break;
		case 57:
			opCode = "dstore";
			break;
		case 58:
			opCode = "astore";
			break;
		case 59:
			opCode = "istore_0";
			break;
		case 60:
			opCode = "istore_1";
			break;
		case 61:
			opCode = "istore_2";
			break;
		case 62:
			opCode = "istore_3";
			break;
		case 63:
			opCode = "lstore_0";
			break;
		case 64:
			opCode = "lstore_1";
			break;
		case 65:
			opCode = "lstore_2";
			break;
		case 66:
			opCode = "lstore_3";
			break;
		case 67:
			opCode = "fstore_0";
			break;
		case 68:
			opCode = "fstore_1";
			break;
		case 69:
			opCode = "fstore_2";
			break;
		case 70:
			opCode = "fstore_3";
			break;
		case 71:
			opCode = "dstore_0";
			break;
		case 72:
			opCode = "dstore_1";
			break;
		case 73:
			opCode = "dstore_2";
			break;
		case 74:
			opCode = "dstore_3";
			break;
		case 75:
			opCode = "astore_0";
			break;
		case 76:
			opCode = "astore_1";
			break;
		case 77:
			opCode = "astore_2";
			break;
		case 78:
			opCode = "astore_3";
			break;
		case 79:
			opCode = "iastore";
			break;
		case 80:
			opCode = "lastore";
			break;
		case 81:
			opCode = "fastore";
			break;
		case 82:
			opCode = "dastore";
			break;
		case 83:
			opCode = "aastore";
			break;
		case 84:
			opCode = "bastore";
			break;
		case 85:
			opCode = "castore";
			break;
		case 86:
			opCode = "sastore";
			break;
		case 87:
			opCode = "pop";
			break;
		case 88:
			opCode = "pop2";
			break;
		case 89:
			opCode = "dup";
			break;
		case 90:
			opCode = "dup_x1";
			break;
		case 91:
			opCode = "dup_x2";
			break;
		case 92:
			opCode = "dup2";
			break;
		case 93:
			opCode = "dup2_x1";
			break;
		case 94:
			opCode = "dup2_x2";
			break;
		case 95:
			opCode = "swap";
			break;
		case 96:
			opCode = "iadd";
			break;
		case 97:
			opCode = "ladd";
			break;
		case 98:
			opCode = "fadd";
			break;
		case 99:
			opCode = "dadd";
			break;
		case 100:
			opCode = "isub";
			break;
		case 101:
			opCode = "lsub";
			break;
		case 102:
			opCode = "fsub";
			break;
		case 103:
			opCode = "dsub";
			break;
		case 104:
			opCode = "imul";
			break;
		case 105:
			opCode = "lmul";
			break;
		case 106:
			opCode = "fmul";
			break;
		case 107:
			opCode = "dmul";
			break;
		case 108:
			opCode = "idiv";
			break;
		case 109:
			opCode = "ldiv";
			break;
		case 110:
			opCode = "fdiv";
			break;
		case 111:
			opCode = "ddiv";
			break;
		case 112:
			opCode = "irem";
			break;
		case 113:
			opCode = "lrem";
			break;
		case 114:
			opCode = "frem";
			break;
		case 115:
			opCode = "drem";
			break;
		case 116:
			opCode = "ineg";
			break;
		case 117:
			opCode = "lneg";
			break;
		case 118:
			opCode = "fneg";
			break;
		case 119:
			opCode = "dneg";
			break;
		case 120:
			opCode = "ishl";
			break;
		case 121:
			opCode = "lshl";
			break;
		case 122:
			opCode = "ishr";
			break;
		case 123:
			opCode = "lshr";
			break;
		case 124:
			opCode = "iushr";
			break;
		case 125:
			opCode = "lushr";
			break;
		case 126:
			opCode = "iand";
			break;
		case 127:
			opCode = "land";
			break;
		case 128:
			opCode = "ior";
			break;
		case 129:
			opCode = "lor";
			break;
		case 130:
			opCode = "ixor";
			break;
		case 131:
			opCode = "lxor";
			break;
		case 132:
			opCode = "iinc";
			break;
		case 133:
			opCode = "i2l";
			break;
		case 134:
			opCode = "i2f";
			break;
		case 135:
			opCode = "i2d";
			break;
		case 136:
			opCode = "l2i";
			break;
		case 137:
			opCode = "l2f";
			break;
		case 138:
			opCode = "l2d";
			break;
		case 139:
			opCode = "f2i";
			break;
		case 140:
			opCode = "f2l";
			break;
		case 141:
			opCode = "f2d";
			break;
		case 142:
			opCode = "d2i";
			break;
		case 143:
			opCode = "d2l";
			break;
		case 144:
			opCode = "d2f";
			break;
		case 145:
			opCode = "i2b";
			break;
		case 146:
			opCode = "i2c";
			break;
		case 147:
			opCode = "i2s";
			break;
		case 148:
			opCode = "lcmp";
			break;
		case 149:
			opCode = "fcmpl";
			break;
		case 150:
			opCode = "fcmpg";
			break;
		case 151:
			opCode = "dcmpl";
			break;
		case 152:
			opCode = "dcmpg";
			break;
		case 153:
			opCode = "ifeq";
			break;
		case 154:
			opCode = "ifne";
			break;
		case 155:
			opCode = "iflt";
			break;
		case 156:
			opCode = "ifge";
			break;
		case 157:
			opCode = "ifgt";
			break;
		case 158:
			opCode = "ifle";
			break;
		case 159:
			opCode = "if_icmpeq";
			break;
		case 160:
			opCode = "if_icmpne";
			break;
		case 161:
			opCode = "if_icmplt";
			break;
		case 162:
			opCode = "if_icmpge";
			break;
		case 163:
			opCode = "if_icmpgt";
			break;
		case 164:
			opCode = "if_icmple";
			break;
		case 165:
			opCode = "if_acmpeq";
			break;
		case 166:
			opCode = "if_acmpne";
			break;
		case 167:
			opCode = "goto";
			break;
		case 168:
			opCode = "jsr";
			break;
		case 169:
			opCode = "ret";
			break;
		case 170:
			opCode = "tableswitch";
			break;
		case 171:
			opCode = "lookupswitch";
			break;
		case 172:
			opCode = "ireturn";
			break;
		case 173:
			opCode = "lreturn";
			break;
		case 174:
			opCode = "freturn";
			break;
		case 175:
			opCode = "dreturn";
			break;
		case 176:
			opCode = "areturn";
			break;
		case 177:
			opCode = "return";
			break;
		case 178:
			opCode = "getstatic";
			break;
		case 179:
			opCode = "putstatic";
			break;
		case 180:
			opCode = "getfield";
			break;
		case 181:
			opCode = "putfield";
			break;
		case 182:
			opCode = "invokevirtual";
			break;
		case 183:
			opCode = "invokespecial";
			break;
		case 184:
			opCode = "invokestatic";
			break;
		case 185:
			opCode = "invokeinterface";
			break;
		case 186:
			opCode = "invokedynamic";
			break;
		case 187:
			opCode = "new";
			break;
		case 188:
			opCode = "newarray";
			break;
		case 189:
			opCode = "anewarray";
			break;
		case 190:
			opCode = "arraylength";
			break;
		case 191:
			opCode = "athrow";
			break;
		case 192:
			opCode = "checkcast";
			break;
		case 193:
			opCode = "instanceof";
			break;
		case 194:
			opCode = "monitorenter";
			break;
		case 195:
			opCode = "monitorexit";
			break;
		case 196:
			opCode = "wide";
			break;
		case 197:
			opCode = "multianewarray";
			break;
		case 198:
			opCode = "ifnull";
			break;
		case 199:
			opCode = "ifnonnull";
			break;
		case 200:
			opCode = "goto_w";
			break;
		case 201:
			opCode = "jsr_w";
			break;
		case 202:
			opCode = "breakpoint";
			break;
		case 254:
			opCode = "impdep1";
			break;
		case 255:
			opCode = "impdep2";
			break;
		default:
			opCode = null;
			break;
		}
		return opCode;

	}

	public static int getNextID() {
		return nextVariableID++;
	}
}
