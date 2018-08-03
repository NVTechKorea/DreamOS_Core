# DreamOS_Core
현재 개발중인 DreamOS의 논그래픽 엔진입니다.
베타버전은 컴파일링 되어있지 않은 .java 상태로 업로드됩니다.
안정버전은 .jar 파일로 컴파일되어 업로드됩니다.

*경고*
Windows 에서는 Fusion Shield 가 정상적으로 작동하지 않는것으로 판단되었습니다.
macOS 또는 Linux 에서 사용해주십시오.

# 사용방법:
명령어
- cd [directory name]
	- 디렉터리로 들어감
- delete (-d) [target]
	- -d 는 디렉터리 삭제, 없으면 파일을 삭제함
- fasthelp
	- 간단한 명령어 보기
- fs [option]
	- 실드의 옵션을 변경 (패스워드 변경 / 계정 삭제)
- help
	- 명령어 자세히 보기
- list
	- 현재 디렉터리에 있는 파일과 하위 디렉터리의 리스트를 봄
- mkdir [name]
	- 디렉터리를 만듬
- read [file name]
	- 파일을 읽음
- reboot
	- OS 재시작
- shutdown
	- OS 종료
- write [file name] [contents]
	- 파일 작성

# 개발 로그:
Engine Run Test 1 (build 0)
- Initial commit

Engine Run Test 2 (build 4)
- Updated boot engine
- Enhanced LibMGR stability

Test Beta 3 (build 18B072742UD-TB3)
- Added command: list
- Added command: fs
- Added command: delete
- Added command: fasthelp
- Added command: help
- Added command: write
- Added command: read
- Added setting modification feature at write command
- Added setting read feature at read command
- Added input indicator: >
- Added unknown command error output
- Repair errors

Test Beta 4 (build18B072826UD-TB4)
- Added command: reboot
- Added command: cd
- Added command: mkdir
- Added command: delete -d
- Updated File reader (Failure when user tried to read directory)
- Added DeleteFolder
- Added MakeDir

Test Beta 5 (build 18B072847UD-TB5)
- Patched custom boot hash causing E:47

Test Beta 6 (build 18B072967UD-TB6)
- Patched root exploit when lock option is enabled
- Patched signature writing variable link
- Patched ReadFile class error
- Patched skipping login when secure option is disabled
- Patched displaying broken root lock option message as a broken signature check option.
- Patched automatic login error

Test Beta 7 (build 18B073127UD-TB7)
- Patched deletable system directory (Permission patch)
- Patched security exploit
- Default boot hash is now the same as signature hash (Not null)
- Started creating migration helper
- Started creating safeOS
- Started creating recoveryOS