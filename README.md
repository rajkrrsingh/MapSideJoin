MapSideJoin
===========

Demonstration of Map side join if the join key data size is too small
Input Data : order_custid.txt
..............................................................................

781571544	S9876126
781571545	S9876127
781571546	S9876128
781571547	S9876129
781571548	S9876130
781571549	S9876131
781571550	S9876132
781571551	S9876133
781571552	S9876134


Customer Data : cust_data.txt
.............................................................................

781571544	Smith,John      (248)-555-9430  jsmith@aol.com
781571545	Hunter,April    (815)-555-3029  april@showers.org
781571546	Ching,Iris      (305)-555-0919  iching@zen.org
781571547	Doe,John        (212)-555-0912  jdoe@morgue.com
781571548	Jones,Tom       (312)-555-3321  tj2342@aol.com
781571549	Smith,John      (607)-555-0023  smith@pocahontas.com
781571550	Crosby,Dave     (405)-555-1516  cros@csny.org
781571551	Johns,Pam       (313)-555-6790  pj@sleepy.com
781571552	Jetter,Linda    (810)-555-8761  netless@earthlink.net



output::
.................................................................................
781571544	Smith,John	(248)-555-9430	jsmith@aol.com	S9876126
781571545	Hunter,April	(815)-555-3029	april@showers.org	S9876127
781571546	Ching,Iris	(305)-555-0919	iching@zen.org	S9876128
781571547	Doe,John	(212)-555-0912	jdoe@morgue.com	S9876129
781571548	Jones,Tom	(312)-555-3321	tj2342@aol.com	S9876130
781571549	Smith,John	(607)-555-0023	smith@pocahontas.com	S9876131
781571550	Crosby,Dave	(405)-555-1516	cros@csny.org	S9876132
781571551	Johns,Pam	(313)-555-6790	pj@sleepy.com	S9876133
781571552	Jetter,Linda	(810)-555-8761	netless@earthlink.net	S9876134
781571552	Jetter,Linda	(810)-555-8761	netless@earthlink.net	S9876134
781571552	Jetter,Linda	(810)-555-8761	netless@earthlink.net	S9876134
