#ifdef BUILD_CAL_DLL // 如果定义了构建，表示生成xxx.dll
	#define EXPORT __declspec(dllexport)
#else                // 否则表示引用xxx.dll
	#define EXPORT __declspec(dllimport)
#endif

/*接口*/
extern "C"{
	EXPORT int addInt(int, int);
	EXPORT int subInt(int, int);
}
